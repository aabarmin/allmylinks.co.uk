import { BlockType } from "@/app/model/Block";
import { getDbClient } from "@/lib/dbClient";
import { z } from "zod";
import { CreateBlockResponse } from "./CrateBlockResponse";
import { CreateBlockRequest } from "./CreateBlockRequest";

export async function POST(
  request: Request,
  { params }: { params: Promise<{ pageId: string }> }
) {
  const pageId = (await params).pageId;
  const requestBody = (await request.json()) as CreateBlockRequest;

  const createRequest = z.object({
    type: z.enum([BlockType.BLOCK_AVATAR, BlockType.BLOCK_HEADER, BlockType.BLOCK_SOCIAL_NETWORKS]),
  });
  const parsingResult = createRequest.safeParse(requestBody);
  if (!parsingResult.success) {
    return Response.json({
      error: 'Invalid request body',
      errors: parsingResult.error
    }, { status: 400 });
  }

  const page = await getDbClient().page.findUnique({
    where: { id: +pageId },
    include: { blocks: true }
  });
  if (page == null) {
    return Response.json({ error: 'Page not found' }, { status: 404 });
  }
  const blocks = page.blocks;
  const newBlock = await getDbClient().block.create({
    data: {
      pageId: +pageId,
      type: requestBody.type,
      props: {},
      order: blocks.length + 1
    }
  });

  return Response.json(new CreateBlockResponse(
    newBlock.id,
    newBlock.order
  ));
}