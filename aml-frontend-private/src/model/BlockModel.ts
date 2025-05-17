import type { AvatarBlockProps } from "./AvatarBlockProps";
import type { BlockTypeModel } from "./BlockTypeModel";
import type { HeaderBlockProps } from "./HeaderBlockProps";
import type { LinkButtonBlockProps } from "./LinkButtonBlockProps";
import type { SocialNetworksBlockProps } from "./SocialNetworksBlockProps";

export type AllowedBlockProps = HeaderBlockProps | LinkButtonBlockProps | AvatarBlockProps | SocialNetworksBlockProps;

export class BlockResponse {
  blockId: number;
  pageId: number;
  blockType: BlockTypeModel;
  blockProps: AllowedBlockProps;
  canMoveUp: boolean;
  canMoveDown: boolean;

  constructor(
    blockId: number,
    pageId: number,
    blockType: BlockTypeModel,
    blockProps: AllowedBlockProps,
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
