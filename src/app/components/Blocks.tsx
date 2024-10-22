'use client';

import { AccountCircle, Title } from "@mui/icons-material";
import { List, ListItemButton, ListItemDecorator } from "@mui/joy";
import { AvatarBlock } from "../blocks/AvatarBlock";
import { HeaderBlock } from "../blocks/HeaderBlock";
import { BlockAddRequest } from "../hooks/BlockAddRequest";
import { useAppState } from "../hooks/StateProvider";

export function Blocks() {
  const { state, dispatch } = useAppState();

  const addAvatar = () => {
    dispatch(new BlockAddRequest(
      state.getCurrentPage(),
      new AvatarBlock(
        state.getCurrentPage().blocks.length,
        state.getCurrentPage().blocks.length
      )
    ))
  };

  const addHeader = () => {
    dispatch(new BlockAddRequest(
      state.getCurrentPage(),
      new HeaderBlock(
        state.getCurrentPage().blocks.length,
        state.getCurrentPage().blocks.length
      )
    ));
  }

  return (
    <List sx={{
      p: 2
    }}>
      <ListItemButton onClick={addAvatar}>
        <ListItemDecorator>
          <AccountCircle />
        </ListItemDecorator>
        Avatar
      </ListItemButton>

      <ListItemButton onClick={addHeader}>
        <ListItemDecorator>
          <Title />
        </ListItemDecorator>
        Header
      </ListItemButton>
    </List>
  );
}