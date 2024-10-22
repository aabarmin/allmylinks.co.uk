import { Title } from "@mui/icons-material";
import { Avatar, Box, List, ListItem, ListItemButton, ListItemDecorator } from "@mui/joy";
import { BlockSelectRequest } from "../hooks/BlockSelectRequest";
import { useAppState } from "../hooks/StateProvider";
import { BlockType } from "../model/Block";

export function PageBlockList() {
  const { state, dispatch } = useAppState();

  const pageBlocks = state.getCurrentPage().blocks
  const blockSelect = (blockId: number) => {
    const block = state.getBlock(blockId)
    if (block != undefined) {
      dispatch(new BlockSelectRequest(block))
    }
  }

  return (
    <Box sx={{ padding: 2 }}>
      <List>
        {pageBlocks.map(block => {
          if (block.type == BlockType.BLOCK_HEADER) {
            return (
              <ListItemButton key={block.id} onClick={() => blockSelect(block.id)}>
                <ListItemDecorator>
                  <Title />
                </ListItemDecorator>
                Header
              </ListItemButton>
            );
          } else if (block.type == BlockType.BLOCK_AVATAR) {
            return (
              <ListItemButton key={block.id} onClick={() => blockSelect(block.id)}>
                <ListItemDecorator>
                  <Avatar />
                </ListItemDecorator>
                Avatar
              </ListItemButton>
            );
          } else {
            return (
              <ListItem key={block.id} onClick={() => blockSelect(block.id)}>
                Unknown block of type {block.type}
              </ListItem>
            )
          }
        })}
      </List>
    </Box>
  );
}