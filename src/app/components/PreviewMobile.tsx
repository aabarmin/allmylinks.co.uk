import { Box, Grid } from "@mui/joy";
import { useCallback, useState } from "react";
import AvatarBlockComponent from "../blocks/AvatarBlock";
import { HeaderBlockComponent, HeaderBlockProps } from "../blocks/HeaderBlock";
import { BlockSelectRequest } from "../hooks/BlockSelectRequest";
import { useAppState } from "../hooks/StateProvider";
import { BlockType } from "../model/Block";

function useSelection() {
  const [hovered, setHovered] = useState<string[]>([]);

  function mouseEnter(blockId: string | null) {
    if (blockId == null) {
      return;
    }
    if (hovered.indexOf(blockId) != -1) {
      return;
    }
    // only one block selected at a time
    setHovered([blockId])
  }

  function mouseLeave(blockId: string | null) {
    if (blockId == null) {
      return;
    }
    setHovered(hovered.filter(id => id != blockId))
  }

  function isHovered(blockId: string | null) {
    if (blockId == null) {
      return false;
    }
    return hovered.indexOf(blockId) != -1;
  }

  return {
    isHovered,
    mouseEnter,
    mouseLeave
  };
}

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
  const { state, dispatch } = useAppState();
  const mouse = useSelection();
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
  const rowSelect = useCallback((blockId: string | null) => {
    if (blockId == null) {
      return;
    }
    const block = state.getBlock(+blockId);
    if (!block) {
      console.warn('Cannot find a block with id ' + blockId)
      return
    }
    dispatch(new BlockSelectRequest(block))
  }, [state]);
  const isActive = useCallback((blockId: string | null) => {
    if (blockId == null) {
      return false;
    }
    if (mouse.isHovered(blockId)) {
      return true;
    }
    if (state.getCurrentBlock()) {
      return state.getCurrentBlock()?.id == (+blockId)
    }
    return false;
  }, [state, mouse]);
  const rows = blocks.map(block => {
    return [
      <Grid
        xs={2}
        key={block?.key + '_left'}
        sx={{
          backgroundColor: isActive(block!.key) ? '#f0f0f0' : '#ffffff'
        }}
        onMouseEnter={() => mouse.mouseEnter(block!.key)}
        onMouseLeave={() => mouse.mouseLeave(block!.key)}
        onClick={() => rowSelect(block!.key)}
      >
        <LeftBlock />
      </Grid>,
      <Grid
        xs={8}
        sx={{
          backgroundColor: isActive(block!.key) ? '#f0f0f0' : '#ffffff'
        }}
        key={block?.key + '_content'}
        onMouseEnter={() => mouse.mouseEnter(block!.key)}
        onMouseLeave={() => mouse.mouseLeave(block!.key)}
        onClick={() => rowSelect(block!.key)}
      >
        {block}
      </Grid>,
      <Grid
        xs={2}
        sx={{
          backgroundColor: isActive(block!.key) ? '#f0f0f0' : '#ffffff'
        }}
        key={block?.key + '_right'}
        onMouseEnter={() => mouse.mouseEnter(block!.key)}
        onMouseLeave={() => mouse.mouseLeave(block!.key)}
        onClick={() => rowSelect(block!.key)}
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