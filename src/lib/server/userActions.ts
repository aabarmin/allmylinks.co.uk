import { auth } from "@/auth";
import { User } from "@prisma/client";
import { Session } from "next-auth";
import { getDbClient } from "./dbClient";
import { EmailTemplate, sendEmail } from "./emailActions";

export async function getCurrentUserId(): Promise<number | null> {
  const user = await getCurrentUser();
  return user?.id || null;
}

export async function isLoggedIn(): Promise<boolean> {
  const session = await auth();
  return Promise.resolve(!!session);
}

export async function getCurrentUser(): Promise<User | null> {
  const session = await auth();
  if (!session) {
    return Promise.resolve(null);
  }
  if (!session.user) {
    return Promise.resolve(null);
  }
  if (!session.user.email) {
    return Promise.resolve(null);
  }
  const email = session.user.email;
  const isKnown = await isKnownUser(email);
  if (!isKnown) {
    const user = await createUserFromSession(session);
    onNewUserRegistered(user);
    return user;
  }
  const user = await getUser(email);
  updateLastLogin(user);
  return user;
}

export async function isKnownUser(email: string): Promise<boolean> {
  if (!email) {
    return Promise.resolve(false);
  }
  const users = await getDbClient().user.count({
    where: { email: email }
  });
  return users > 0;
}

export async function getUser(email: string): Promise<User> {
  return getDbClient().user.findUniqueOrThrow({
    where: { email: email }
  })
}

export async function updateLastLogin(user: User): Promise<User> {
  return getDbClient().user.update({
    where: { id: user.id },
    data: { lastLogin: new Date() }
  })
}

export async function createUserFromSession(session: Session): Promise<User> {
  const user = session.user || {};

  return getDbClient().user.create({
    data: {
      email: user.email as string,
      name: user.name as string,
      image: user.image as string,
      isActive: true,
      provider: 'JWT'
    }
  })
}

export async function onNewUserRegistered(user: User): Promise<void> {
  return Promise.all([
    sendEmail(user, EmailTemplate.REGISTERED)
  ]).then();
}

export async function deactivateUser(user: User): Promise<User> {
  return user;
}
