'use client';

import { AccountCircle, ThumbUp, Title } from "@mui/icons-material";
import { List, ListItemButton, ListItemDecorator } from "@mui/joy";
import { AvatarBlock } from "../blocks/AvatarBlock";
import { HeaderBlock } from "../blocks/HeaderBlock";
import { SocialNetworksBlock } from "../blocks/SocialNetworksBlock";
import { BlockAddRequest } from "../hooks/block/BlockAddRequest";
import { useAppState } from "../hooks/StateProvider";

export function Blocks() {
  const { state, dispatch } = useAppState();

  const addAvatar = () => {
    dispatch(new BlockAddRequest(
      state.getCurrentPage(),
      new AvatarBlock(
        state.getCurrentPage().id * 100 + state.getCurrentPage().blocks.length,
        state.getCurrentPage().id * 100 + state.getCurrentPage().blocks.length
      )
    ))
  };

  const addHeader = () => {
    dispatch(new BlockAddRequest(
      state.getCurrentPage(),
      new HeaderBlock(
        state.getCurrentPage().id * 100 + state.getCurrentPage().blocks.length,
        state.getCurrentPage().id * 100 + state.getCurrentPage().blocks.length
      )
    ));
  }

  const addSocialNetworks = () => {
    dispatch(new BlockAddRequest(
      state.getCurrentPage(),
      new SocialNetworksBlock(
        state.getCurrentPage().id * 100 + state.getCurrentPage().blocks.length,
        state.getCurrentPage().id * 100 + state.getCurrentPage().blocks.length
      )
    ))
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

      <ListItemButton onClick={addSocialNetworks}>
        <ListItemDecorator>
          <ThumbUp />
        </ListItemDecorator>
        Social Networks
      </ListItemButton>
    </List>
  );
}