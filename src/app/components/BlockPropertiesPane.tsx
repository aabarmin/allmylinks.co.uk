import { Box } from "@mui/joy";
import { AvatarBlockProperties } from "../blocks/AvatarBlock";
import { HeaderBlockProperties, HeaderBlockProps } from "../blocks/HeaderBlock";
import { useAppState } from "../hooks/StateProvider";
import { Block, BlockType } from "../model/Block";

function NoBlockSelected() {
  return (
    <div></div>
  );
}

function NoBlockProperties({ type }: { type: BlockType }) {
  return (
    <div>No properties for block of type {type}</div>
  );
}

function getBlockProperties(block: Block<any> | undefined) {
  if (!block) {
    return <NoBlockSelected />
  }
  switch (block.type) {
    case BlockType.BLOCK_AVATAR: {
      return <AvatarBlockProperties />
    }
    case BlockType.BLOCK_HEADER: {
      const props = block.props as HeaderBlockProps
      return <HeaderBlockProperties {...props} />
    }
    default: return <NoBlockProperties type={block.type} />
  }
}

export function BlockPropertiesPane() {
  const { state } = useAppState();
  const properties = getBlockProperties(state.getCurrentBlock());

  return (
    <Box>{properties}</Box>
  );
}