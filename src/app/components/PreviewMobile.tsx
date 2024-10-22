import { Box, Grid } from "@mui/joy";
import { useCallback } from "react";
import AvatarBlockComponent from "../blocks/AvatarBlock";
import { HeaderBlockComponent, HeaderBlockProps } from "../blocks/HeaderBlock";
import { useAppState } from "../hooks/StateProvider";
import { BlockType } from "../model/Block";

function LeftBlock() {
  return (
    <div></div>
  );
}

function RightBlock() {
  return (
    <div></div>
  );
}

export default function PreviewMobile() {
  const { state } = useAppState();
  const blocks = state.getCurrentPage().blocks.map(block => {
    switch (block.type) {
      case BlockType.BLOCK_AVATAR: {
        return <AvatarBlockComponent key={block.id} />
      }
      case BlockType.BLOCK_HEADER: {
        const props = block.props as HeaderBlockProps
        return <HeaderBlockComponent key={block.id} {...props} />
      }
      default: <div key={999}>No block of type {block.type}</div>
    }
  });
  const isActive = useCallback((blockId: string | null) => {
    if (blockId == null) {
      return false;
    }
    if (state.getCurrentBlock()) {
      return state.getCurrentBlock()?.id == (+blockId)
    }
    return false;
  }, [state]);
  const rows = blocks.map(block => {
    return [
      <Grid
        xs={2}
        key={block?.key + '_left'}
        sx={{
          backgroundColor: isActive(block!.key) ? '#f0f0f0' : '#ffffff'
        }}
      >
        <LeftBlock />
      </Grid>,
      <Grid
        xs={8}
        sx={{
          backgroundColor: isActive(block!.key) ? '#f0f0f0' : '#ffffff'
        }}
        key={block?.key + '_content'}
      >
        {block}
      </Grid>,
      <Grid
        xs={2}
        sx={{
          backgroundColor: isActive(block!.key) ? '#f0f0f0' : '#ffffff'
        }}
        key={block?.key + '_right'}
      >
        <RightBlock />
      </Grid>
    ]
  }).flat();

  return (
    <Box>
      <Grid container>
        {rows}
      </Grid>
    </Box>
  );
}