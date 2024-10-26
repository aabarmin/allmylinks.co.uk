import { Page, Profile } from "@prisma/client";
import { getDb } from "./dbClient";

export async function getCurrentProfile(userId: number): Promise<Profile> {
  return getDb().profile.findFirstOrThrow({
    where: { userId: userId },
    orderBy: { createdAt: 'desc' }
  })
}

export async function getPages(profile: Profile): Promise<Page[]> {
  return getDb().page.findMany({
    where: { profile: profile }
  })
}