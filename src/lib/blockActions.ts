import { CreateBlockResponse } from "@/app/(api)/api/pages/[pageId]/blocks/CrateBlockResponse";
import { BlockType } from "@/app/model/Block";
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