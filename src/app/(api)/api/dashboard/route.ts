import { getDbClient } from "@/lib/dbClient";
import { getCurrentUserId } from "@/lib/userActions";
import { Block, Page } from "@prisma/client";
import { DashboardResponse } from "./DashboardResponse";
import { toPageResponse } from "./PageResponse";
import { toProfileResponse } from "./ProfileResponse";

export type PageWithBlocks = Page & { blocks: Block[] };

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
  const pages: PageWithBlocks[] = await getDbClient().page.findMany({
    where: { profileId: profile.id },
    include: { blocks: true }
  });

  return Response.json(new DashboardResponse(
    toProfileResponse(profile),
    toPageResponse(pages)
  ));
}