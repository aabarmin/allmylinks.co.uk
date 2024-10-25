export interface Block<T extends object> {
  id: number;
  type: BlockType;
  order: number;
  props: T;
}

export const enum BlockType {
  BLOCK_AVATAR = 'BLOCK_AVATAR',
  BLOCK_HEADER = 'BLOCK_HEADER',
  BLOCK_SOCIAL_NETWORKS = 'BLOCK_SOCIAL_NETWORKS'
}