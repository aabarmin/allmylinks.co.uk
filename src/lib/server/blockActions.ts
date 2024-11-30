import { Block } from "@prisma/client";
import { getDbClient } from "./dbClient";

async function getPageBlocks(pageId: number) {
  return await getDbClient().block.findMany({
    where: {
      pageId: pageId,
      isDeleted: false,
    },
    orderBy: { order: "asc" },
  });
}

export async function getBlock(id: number) {
  return await getDbClient().block.findUnique({
    where: { id },
  });
}

export async function deleteBlock(block: Block) {
  const blocks = await getPageBlocks(block.pageId);
  await getDbClient().$transaction(async (tx) => {
    let index = 1;
    for (const b of blocks) {
      if (b.id === block.id) {
        await tx.block.update({
          where: { id: b.id },
          data: {
            isDeleted: true,
            order: 999
          },
        });
        continue;
      }
      await tx.block.update({
        where: { id: b.id },
        data: { order: index },
      });
      index++;
    }
  });
}

export async function moveBlock(block: Block, direction: "up" | "down") {
  const blocks = await getPageBlocks(block.pageId);
  const currentIndex = blocks.findIndex(b => b.id === block.id);
  if (direction === "up" && block.order === 1) {
    return;
  } else if (direction === "down" && block.order === blocks.length) {
    return;
  }
  if (direction === "up") {
    [blocks[currentIndex], blocks[currentIndex - 1]] = [blocks[currentIndex - 1], blocks[currentIndex]];
  } else if (direction === "down") {
    [blocks[currentIndex], blocks[currentIndex + 1]] = [blocks[currentIndex + 1], blocks[currentIndex]];
  }
  await getDbClient().$transaction(async (tx) => {
    for (let i = 0; i < blocks.length; i++) {
      await tx.block.update({
        where: { id: blocks[i].id },
        data: { order: i + 1 },
      });
    }
  });
}