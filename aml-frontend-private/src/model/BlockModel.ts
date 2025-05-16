import type { BlockTypeModel } from "./BlockTypeModel";

export class BlockModel {
  blockId: number;
  pageId: number;
  blockType: BlockTypeModel;
  blockProps: object; // todo, abarmin: fix it
  canMoveUp: boolean;
  canMoveDown: boolean;

  constructor(
    blockId: number,
    pageId: number,
    blockType: BlockTypeModel,
    blockProps: object,
    canMoveUp: boolean,
    canMoveDown: boolean
  ) {
    this.blockId = blockId;
    this.pageId = pageId;
    this.blockType = blockType;
    this.blockProps = blockProps;
    this.canMoveUp = canMoveUp;
    this.canMoveDown = canMoveDown;
  }
}