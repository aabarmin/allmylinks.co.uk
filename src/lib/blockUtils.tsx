import { AvatarBlock, AvatarBlockOptionalProps, AvatarBlockProps, fromOptionalProps as avatarFromOptionalProps } from "@/app/blocks/avatar/AvatarBlock";
import AvatarBlockComponent from "@/app/blocks/avatar/AvatarBlockComponent";
import { HeaderBlockProps } from "@/app/blocks/header/HeaderBlock";
import { HeaderBlockComponent } from "@/app/blocks/header/HeaderBlockComponent";
import { SocialNetworksBlockProps } from "@/app/blocks/networks/SocialNetworksBlock";
import { SocialNetworksBlockComponent } from "@/app/blocks/networks/SocialNetworksBlockComponent";
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
      const props = record.props as AvatarBlockOptionalProps
      return new AvatarBlock(
        record.id,
        record.order,
        avatarFromOptionalProps(props)
      )
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