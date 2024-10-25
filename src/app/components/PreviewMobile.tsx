'use client';

import { Box } from "@mui/joy";
import AvatarBlockComponent, { AvatarBlockProps } from "../blocks/AvatarBlock";
import { HeaderBlockComponent, HeaderBlockProps } from "../blocks/HeaderBlock";
import { SocialNetworksBlockComponent, SocialNetworksBlockProps } from "../blocks/SocialNetworksBlock";
import { useAppState } from "../hooks/StateProvider";
import { BlockType } from "../model/Block";

function MobileFrame({ blocks }: { blocks: React.ReactNode[] }) {
  return (
    <div style={{
      padding: 8,
      border: '5px solid #333',
      borderRadius: '3em',
      maxWidth: '530px',
      minWidth: '530px',
      minHeight: '954px'
    }}>
      {blocks}
    </div>
  );
}

export default function PreviewMobile() {
  const { state } = useAppState();
  const blocks = state.getCurrentPage().blocks.map(block => {
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
  });

  return (
    <Box sx={{
      p: 2,
      display: 'flex',
      justifyContent: 'center'
    }}>
      <MobileFrame blocks={blocks} />
    </Box>
  );
}