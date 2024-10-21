export interface Block<T> {
  id: number;
  type: BlockType;
  order: number;
  props: T;
}

export const enum BlockType {
  BLOCK_AVATAR = 'BLOCK_AVATAR',
  BLOCK_HEADER = 'BLOCK_HEADER'
}