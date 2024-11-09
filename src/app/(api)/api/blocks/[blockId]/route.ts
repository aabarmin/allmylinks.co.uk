import { deleteBlock } from "@/lib/server/blockActions";
import { getDbClient } from "@/lib/server/dbClient";
import { z } from "zod";
import { respondNotFound } from "../../restUtils";
import { UpdateBlockRequest } from "./UpdateBlockRequest";

export async function DELETE(
  request: Request,
  { params }: { params: Promise<{ blockId: string }> }
) {
  const blockId = (await params).blockId;

  const block = await getDbClient().block.findUnique({
    where: { id: +blockId },
  })
  if (!block) {
    return respondNotFound("Block not found")
  }
  await deleteBlock(block)

  return Response.json({ 'ok': true })
}

export async function PATCH(
  request: Request,
  { params }: { params: Promise<{ blockId: string }> }
) {
  const requestBody = await request.json()
  const blockId = (await params).blockId;

  const updateRequest = z.object({
    id: z.number(),
    props: z.unknown()
  })
  const parsedBody = updateRequest.safeParse(requestBody)
  if (!parsedBody.success) {
    return Response.json({ 'error': 'Invalid request', 'errors': parsedBody.error }, { status: 400 })
  }

  const block = await getDbClient().block.findUnique({
    where: { id: +blockId },
  })
  if (!block) {
    return Response.json({ 'error': 'Block not found' }, { status: 404 })
  }
  const body = parsedBody.data as UpdateBlockRequest
  await getDbClient().block.update({
    data: {
      props: body.props,
      updatedAt: new Date()
    },
    where: {
      id: body.id
    }
  })

  return Response.json({ 'ok': true })
}