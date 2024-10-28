import { Block, BlockType } from "@/app/model/Block";

export class AvatarBlock implements Block<AvatarBlockProps> {
  id: number;
  type: BlockType;
  order: number;
  props: AvatarBlockProps;

  constructor(id: number, order: number) {
    this.id = id;
    this.order = order;
    this.type = BlockType.BLOCK_AVATAR;
    this.props = new AvatarBlockProps();
  }
}

export class AvatarBlockProps {
  imageUrl: string = DEFAULT_AVATAR;
}

export const DEFAULT_AVATAR = "/avatar_placeholder.jpeg";

