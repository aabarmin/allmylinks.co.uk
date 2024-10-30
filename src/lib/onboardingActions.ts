import { Onboarding, User } from "@prisma/client";
import { getDbClient } from "./dbClient";

export async function isOnboardingCompleted(user: User): Promise<boolean> {
  const onboarding = await getDbClient().onboarding.findUnique({
    where: { userId: user.id }
  })
  if (!onboarding) {
    return Promise.resolve(false);
  }
  return Promise.resolve(onboarding.isCompleted);
}

export async function getOrCreateOnboarding(user: User): Promise<Onboarding> {
  const onboarding = await getDbClient().onboarding.findUnique({
    where: { userId: user.id }
  });
  if (onboarding) {
    return Promise.resolve(onboarding);
  }
  return getDbClient().onboarding.create({
    data: {
      link: '',
      name: '',
      userId: user.id,
      isCompleted: false
    }
  });
}