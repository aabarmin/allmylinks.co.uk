import { getDbClient } from "@/lib/dbClient";
import { getCurrentUserId } from "@/lib/userActions";
import { Page, Profile } from "@prisma/client";

export class GetDashboardResponse {
  profile: Profile;
  pages: Page[];

  constructor(profile: Profile, pages: Page[]) {
    this.profile = profile;
    this.pages = pages;
  }
}

export async function GET() {
  const currentUserId = await getCurrentUserId();
  if (currentUserId === null) {
    return Response.json({ message: 'You are not logged in' }, { status: 401 });
  }
  const profile = await getDbClient().profile.findUnique({
    where: { userId: currentUserId }
  })
  if (profile == null) {
    return Response.json({ message: 'You do not have a profile' }, { status: 500 });
  }
  const pages = await getDbClient().page.findMany({
    where: { profileId: profile.id }
  });

  return Response.json(new GetDashboardResponse(profile, pages));
}