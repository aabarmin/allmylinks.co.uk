'use client';

import { addBlock } from "@/lib/blockActions";
import { AccountCircle, ThumbUp, Title } from "@mui/icons-material";
import { LinearProgress, List, ListItemButton, ListItemDecorator } from "@mui/joy";
import { useState } from "react";
import { AvatarBlock, AvatarBlockProps } from "../blocks/avatar/AvatarBlock";
import { HeaderBlock, HeaderBlockProps } from "../blocks/header/HeaderBlock";
import { SocialNetworksBlock } from "../blocks/networks/SocialNetworksBlock";
import { BlockAddRequest } from "../hooks/block/BlockAddRequest";
import { useAppState } from "../hooks/StateProvider";
import { BlockType } from "../model/Block";

export function Blocks() {
  const { state, dispatch } = useAppState();
  const [isLoading, setLoading] = useState<boolean>(false);

  const addAvatar = () => {
    setLoading(true)
    addBlock(state.getCurrentPage(), BlockType.BLOCK_AVATAR)
      .then(response => {
        setLoading(false)
        dispatch(new BlockAddRequest(
          state.getCurrentPage(),
          new AvatarBlock(
            response.id,
            response.order,
            new AvatarBlockProps()
          )
        ));
      })
  };

  const addHeader = () => {
    setLoading(true)
    addBlock(state.getCurrentPage(), BlockType.BLOCK_HEADER)
      .then(response => {
        setLoading(false)
        dispatch(new BlockAddRequest(
          state.getCurrentPage(),
          new HeaderBlock(
            response.id,
            response.order,
            new HeaderBlockProps()
          )
        ))
      });
  }

  const addSocialNetworks = () => {
    setLoading(true)
    addBlock(state.getCurrentPage(), BlockType.BLOCK_SOCIAL_NETWORKS)
      .then(response => {
        setLoading(false)
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
    <>
      {isLoading && <LinearProgress />}
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
    </>
  );
}