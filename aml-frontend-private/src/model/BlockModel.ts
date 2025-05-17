import type { BlockProps } from "./BlockProps";
import type { BlockTypeModel } from "./BlockTypeModel";
import type { HeaderBlockProps } from "./HeaderBlockProps";

export type AlloweBlockProps = HeaderBlockProps | LinkButtonBlockProps | AvatarBlockProps | SocialNetworksBlockProps;

export class BlockModel {
  blockId: number;
  pageId: number;
  blockType: BlockTypeModel;
  blockProps: AlloweBlockProps;
  canMoveUp: boolean;
  canMoveDown: boolean;

  constructor(
    blockId: number,
    pageId: number,
    blockType: BlockTypeModel,
    blockProps: AlloweBlockProps,
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

export interface LinkButtonBlockProps extends BlockProps { }
export interface AvatarBlockProps extends BlockProps { }
export interface SocialNetworksBlockProps extends BlockProps { }
