import { getDbClient } from "@/lib/server/dbClient";
import { getCurrentUserId } from "@/lib/server/userActions";
import { Block, Page } from "@prisma/client";
import { respondServerError, respondUnauthenticated } from "../restUtils";
import { DashboardResponse } from "./DashboardResponse";
import { toPageResponse } from "./PageResponse";
import { toProfileResponse } from "./ProfileResponse";

export type PageWithBlocks = Page & { blocks: Block[] };

export async function GET() {
  const currentUserId = await getCurrentUserId();
  if (currentUserId === null) {
    return respondUnauthenticated();
  }
  const profile = await getDbClient().profile.findUnique({
    where: { userId: currentUserId }
  })
  if (profile == null) {
    return respondServerError('You don not have a profile');
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