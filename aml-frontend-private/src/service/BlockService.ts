import axios from "axios";
import { plainToInstance } from "class-transformer";
import { BlockResponse } from "../model/BlockModel";
import type { BlockTypeModel } from "../model/BlockTypeModel";
import type { PageModel } from "../model/PageModel";

class BlockAddRequest {
    blockType: string;

    constructor(blockType: string) {
        this.blockType = blockType;
    }
}

export function addBlock(block: BlockTypeModel, page: PageModel): Promise<BlockResponse> {
    const request = new BlockAddRequest(
        block.type
    );

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
