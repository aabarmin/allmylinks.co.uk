import { UpdateBlockRequest } from "@/app/(api)/api/blocks/[blockId]/UpdateBlockRequest";
import { CreateBlockResponse } from "@/app/(api)/api/pages/[pageId]/blocks/CrateBlockResponse";
import { Block, BlockType } from "@/app/model/Block";
import { Page } from "@/app/model/Page";

export async function addBlock(page: Page, blockType: BlockType): Promise<CreateBlockResponse> {
  const response = await fetch(`/api/pages/${page.id}/blocks`, {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json'
    },
    body: JSON.stringify({
      type: blockType
    })
  });

  return await response.json();
}

export async function updateBlock<T extends object>(block: Block<T>, props: T): Promise<void> {
  const response = await fetch(`/api/blocks/${block.id}`, {
    method: 'PATCH',
    headers: {
      'Content-Type': 'application/json'
    },
    body: JSON.stringify({
      id: block.id,
      props: props
    } as UpdateBlockRequest)
  });

  return await response.json();
}

export async function moveUpBlock(block: Block<object>): Promise<void> {
  const response = await fetch(`/api/blocks/${block.id}/up`, {
    method: 'GET',
    headers: {
      'Content-Type': 'application/json'
    },
  });

  return await response.json();
}

export async function moveDownBlock(block: Block<object>): Promise<void> {
  const response = await fetch(`/api/blocks/${block.id}/down`, {
    method: 'GET',
    headers: {
      'Content-Type': 'application/json'
    },
  });

  return await response.json();
}

export async function deleteBlock(block: Block<object>): Promise<void> {
  const response = await fetch(`/api/blocks/${block.id}`, {
    method: 'DELETE',
    headers: {
      'Content-Type': 'application/json'
    },
  });

  return await response.json();
}
