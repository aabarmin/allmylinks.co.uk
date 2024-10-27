'use client';

import { addBlock } from "@/lib/blockActions";
import { AccountCircle, ThumbUp, Title } from "@mui/icons-material";
import { List, ListItemButton, ListItemDecorator } from "@mui/joy";
import { AvatarBlock } from "../blocks/AvatarBlock";
import { HeaderBlock } from "../blocks/HeaderBlock";
import { SocialNetworksBlock } from "../blocks/SocialNetworksBlock";
import { BlockAddRequest } from "../hooks/block/BlockAddRequest";
import { useAppState } from "../hooks/StateProvider";
import { BlockType } from "../model/Block";

export function Blocks() {
  const { state, dispatch } = useAppState();

  const addAvatar = () => {
    addBlock(state.getCurrentPage(), BlockType.BLOCK_AVATAR)
      .then(response => {
        dispatch(new BlockAddRequest(
          state.getCurrentPage(),
          new AvatarBlock(
            response.id,
            response.order
          )
        ));
      })
  };

  const addHeader = () => {
    addBlock(state.getCurrentPage(), BlockType.BLOCK_HEADER)
      .then(response => {
        dispatch(new BlockAddRequest(
          state.getCurrentPage(),
          new HeaderBlock(
            response.id,
            response.order
          )
        ))
      });
  }

  const addSocialNetworks = () => {
    addBlock(state.getCurrentPage(), BlockType.BLOCK_SOCIAL_NETWORKS)
      .then(response => {
        dispatch(new BlockAddRequest(
          state.getCurrentPage(),
          new SocialNetworksBlock(
            response.id,
            response.order
          )
        ))
      });
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