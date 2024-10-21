'use client';

import { Avatar } from "@mui/joy";
import { Block, BlockType } from "../model/Block";

export class BlockAvatarProps { }

export class AvatarBlock implements Block<BlockAvatarProps> {
  id: number;
  type: BlockType;
  order: number;
  props: BlockAvatarProps;

  constructor(id: number, order: number) {
    this.id = id;
    this.order = order;
    this.type = BlockType.BLOCK_AVATAR;
    this.props = new BlockAvatarProps();
  }
}

export function AvatarBlockProperties() {
  return (
    <div>Properties form</div>
  );
}

export default function AvatarBlockComponent() {
  return (
    <Avatar>
      AB
    </Avatar>
  );
}