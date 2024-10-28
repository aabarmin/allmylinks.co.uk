import { AvatarBlockProps } from "@/app/blocks/avatar/AvatarBlock";
import AvatarBlockComponent from "@/app/blocks/avatar/AvatarBlockComponent";
import { HeaderBlockComponent, HeaderBlockProps } from "@/app/blocks/HeaderBlock";
import { SocialNetworksBlockComponent, SocialNetworksBlockProps } from "@/app/blocks/SocialNetworksBlock";
import { Block, BlockType } from "@/app/model/Block";
import { Block as BlockRecord } from "@prisma/client";

export function blocksToReactNodes(blocks: Block<object>[]): React.ReactNode[] {
  return blocks.map(blockToReactNode);
}

export function recordsToBlocks(records: BlockRecord[]): Block<object>[] {
  return records.map(recordToBlock);
}

export function recordToBlock(record: BlockRecord): Block<object> {
  switch (record.blockType) {
    case BlockType.BLOCK_AVATAR: {
      // const props = record.props as {
      //   imageUrl?: string
      // }
    }
  }

  throw new Error(`Block of type ${record.blockType} not supported`);
}

function blockToReactNode(block: Block<object>): React.ReactNode {
  switch (block.type) {
    case BlockType.BLOCK_AVATAR: {
      const props = block.props as AvatarBlockProps
      return <AvatarBlockComponent key={block.id} {...props} />
    }
    case BlockType.BLOCK_HEADER: {
      const props = block.props as HeaderBlockProps
      return <HeaderBlockComponent key={block.id} {...props} />
    }
    case BlockType.BLOCK_SOCIAL_NETWORKS: {
      const props = block.props as SocialNetworksBlockProps
      return <SocialNetworksBlockComponent key={block.id} {...props} />
    }
    default: <div key={999}>No block of type {block.type}</div>
  }
}