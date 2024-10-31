import { User } from "@prisma/client";
import { getDbClient } from "../dbClient";

export async function isOnboardingCompleted(user: User): Promise<boolean> {
  const onboarding = await getDbClient().onboarding.findUnique({
    where: { userId: user.id }
  })
  if (!onboarding) {
    return Promise.resolve(false);
  }
  return Promise.resolve(onboarding.isCompleted);
}
