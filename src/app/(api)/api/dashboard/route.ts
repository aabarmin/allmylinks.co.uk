import { getDbClient } from "@/lib/server/dbClient";
import { getCurrentUserId } from "@/lib/server/userActions";
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
    include: {
      blocks: {
        orderBy: {
          order: 'asc'
        },
        where: {
          isDeleted: false
        }
      }
    },
    orderBy: {
      isHome: 'desc'
    }
  });

  return Response.json(new DashboardResponse(
    toProfileResponse(profile),
    toPageResponse(pages)
  ));
}