import { getDbClient } from "@/lib/dbClient";
import { getCurrentUserId } from "@/lib/userActions";
import { PageResponse, toPageResponse } from "./PageResponse";
import { ProfileResponse, toProfileResponse } from "./ProfileResponse";

export class GetDashboardResponse {
  profile: ProfileResponse;
  pages: PageResponse[];

  constructor(profile: ProfileResponse, pages: PageResponse[]) {
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

  return Response.json(new GetDashboardResponse(
    toProfileResponse(profile),
    pages.map(p => toPageResponse(p))
  ));
}