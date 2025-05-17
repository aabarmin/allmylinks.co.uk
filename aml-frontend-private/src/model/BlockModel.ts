import type { AvatarBlockProps } from "./AvatarBlockProps";
import type { BlockProps } from "./BlockProps";
import type { BlockTypeModel } from "./BlockTypeModel";
import type { HeaderBlockProps } from "./HeaderBlockProps";
import type { LinkButtonBlockProps } from "./LinkButtonBlockProps";

export type AlloweBlockProps = HeaderBlockProps | LinkButtonBlockProps | AvatarBlockProps | SocialNetworksBlockProps;

export class BlockResponse {
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

export interface SocialNetworksBlockProps extends BlockProps { }
