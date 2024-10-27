import { getDbClient } from "@/lib/dbClient";
import { getCurrentUserId } from "@/lib/userActions";
import { Page, Profile } from "@prisma/client";

export class GetDashboardResponse {
  profile: ProfileResponse;
  pages: Page[];

  constructor(profile: ProfileResponse, pages: Page[]) {
    this.profile = profile;
    this.pages = pages;
  }
}

export type ProfileResponse = {
  profileActive: boolean;
  profileSlug: string;
  profileLink: string;
  profileDisplayLink: string;
}

function toProfileResponse(profile: Profile): ProfileResponse {
  const profileUrl = `${process.env.BASE_URL}/l/${profile.link}`;
  return {
    profileActive: profile.active,
    profileSlug: profile.link,
    profileLink: profileUrl,
    profileDisplayLink: profileUrl.substring(profileUrl.indexOf('://') + 3)
  };
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

  return Response.json(new GetDashboardResponse(
    toProfileResponse(profile),
    pages
  ));
}