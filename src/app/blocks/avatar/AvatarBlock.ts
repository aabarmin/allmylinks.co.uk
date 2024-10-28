import { Block, BlockType } from "@/app/model/Block";

export const DEFAULT_AVATAR = "/avatar_placeholder.jpeg";

export class AvatarBlock implements Block<AvatarBlockProps> {
  id: number;
  type: BlockType;
  order: number;
  props: AvatarBlockProps;

  constructor(id: number, order: number, props: AvatarBlockProps) {
    this.id = id;
    this.order = order;
    this.type = BlockType.BLOCK_AVATAR;
    this.props = props;
  }
}

export class AvatarBlockProps {
  imageUrl: string = DEFAULT_AVATAR;
}

export type AvatarBlockOptionalProps = {
  imageUrl?: string;
}

export function fromOptionalProps(p: AvatarBlockOptionalProps): AvatarBlockProps {
  const props = new AvatarBlockProps();
  props.imageUrl = p.imageUrl || DEFAULT_AVATAR;
  return props;
}
