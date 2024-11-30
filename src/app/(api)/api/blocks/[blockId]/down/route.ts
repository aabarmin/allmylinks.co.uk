import { getBlock, moveBlock } from "@/lib/server/blockActions";
import { respondNotFound } from "../../../restUtils";

export async function GET(
  request: Request,
  { params }: { params: Promise<{ blockId: string }> }
) {
  const blockId = (await params).blockId;
  const block = await getBlock(+blockId);
  if (!block) {
    return respondNotFound("Block not found");
  }
  await moveBlock(block, "down");

  return Response.json({ ok: true });
}