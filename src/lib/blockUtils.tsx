import { AvatarBlock, AvatarBlockOptionalProps, AvatarBlockProps, fromOptionalProps as avatarFromOptionalProps } from "@/app/blocks/avatar/AvatarBlock";
import AvatarBlockComponent from "@/app/blocks/avatar/AvatarBlockComponent";
import { HeaderBlock, HeaderBlockOptionalProps, HeaderBlockProps, fromOptionalProps as headerFromOptionalProps } from "@/app/blocks/header/HeaderBlock";
import { HeaderBlockComponent } from "@/app/blocks/header/HeaderBlockComponent";
import { fromOptionalProps as snFromOptionalProps, SocialNetworksBlock, SocialNetworksBlockProps, SocialNetworksOptionalProps } from "@/app/blocks/networks/SocialNetworksBlock";
import { SocialNetworksBlockComponent } from "@/app/blocks/networks/SocialNetworksBlockComponent";
import { Block, BlockType } from "@/app/model/Block";
import { BlockLikeRecord } from "@/app/model/BlockLikeRecord";

export function blocksToReactNodes(blocks: Block<object>[]): React.ReactNode[] {
  return blocks.map(blockToReactNode);
}

export function recordsToBlocks(records: BlockLikeRecord[]): Block<object>[] {
  return records.map(recordToBlock);
}

export function recordToBlock(record: BlockLikeRecord): Block<object> {
  switch (record.type) {
    case BlockType.BLOCK_AVATAR: {
      const props = record.props as AvatarBlockOptionalProps
      return new AvatarBlock(
        record.id,
        record.order,
        avatarFromOptionalProps(props)
      )
    }
    case BlockType.BLOCK_HEADER: {
      const props = record.props as HeaderBlockOptionalProps;
      return new HeaderBlock(
        record.id,
        record.order,
        headerFromOptionalProps(props)
      );
    }
    case BlockType.BLOCK_SOCIAL_NETWORKS: {
      const props = record.props as SocialNetworksOptionalProps
      return new SocialNetworksBlock(record.id, record.order, snFromOptionalProps(props));
    }
  }

  throw new Error(`Block of type ${record.type} not supported`);
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