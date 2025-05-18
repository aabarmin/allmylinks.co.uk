import axios from "axios";
import { plainToInstance } from "class-transformer";
import { BlockResponse, type BlockPropsTypes } from "../model/BlockModel";
import type { BlockType, BlockTypeModel } from "../model/BlockTypeModel";
import type { PageModel } from "../model/PageModel";

export function addBlock(blockType: BlockTypeModel, page: PageModel): Promise<BlockResponse> {
  class BlockAddRequest {
    blockType: string;

    constructor(blockType: string) {
      this.blockType = blockType
    }
  }

  const request = new BlockAddRequest(blockType.type)

  return new Promise(resolve => {
    axios
      .post(`/private/api/pages/${page.pageId}/blocks`, request)
      .then(response => {
        const data = response.data as Record<string, unknown>
        const model = plainToInstance(BlockResponse, data)
        resolve(model);
      })
  });
}

export function getBlock(blockId: number): Promise<BlockResponse> {
  return new Promise(resolve => {
    axios
      .get(`/private/api/blocks/${blockId}`)
      .then(response => {
        const data = response.data as Record<string, unknown>
        const model = plainToInstance(BlockResponse, data)
        resolve(model);
      })
  });
}

export function updateBlockProperties(block: BlockResponse, props: BlockPropsTypes): Promise<BlockResponse> {
  class BlockPropsUpdateRequest {
    blockId: number;
    blockType: BlockType;
    blockProps: BlockPropsTypes;

    constructor(blockId: number, blockType: BlockType, blockProps: BlockPropsTypes) {
      this.blockId = blockId
      this.blockType = blockType
      this.blockProps = blockProps
    }
  }

  const request = new BlockPropsUpdateRequest(
    block.blockId,
    block.blockType.type,
    props
  )

  return new Promise(resolve => {
    axios
      .put(`/private/api/blocks/${block.blockId}/props`, request)
      .then(response => {
        const data = response.data as Record<string, unknown>
        const model = plainToInstance(BlockResponse, data)
        resolve(model)
      })
  })
}
