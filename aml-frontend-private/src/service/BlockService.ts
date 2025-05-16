import axios from "axios";
import type { BlockTypeModel } from "../model/BlockTypeModel";
import type { PageModel } from "../model/PageModel";

class BlockAddRequest {
    blockType: string;

    constructor(blockType: string) {
        this.blockType = blockType;
    }
}

export function addBlock(block: BlockTypeModel, page: PageModel): Promise<void> {
    const request = new BlockAddRequest(
        block.type
    );

    return new Promise(resolve => {
        axios
            .post(`/private/api/dashboard/pages/${page.pageId}`, request)
            .then(response => {
                debugger
                resolve();
            })
    });
}