'use client';

import { addBlock } from "@/lib/client/blockActions";
import { AccountCircle, AddLink, Title } from "@mui/icons-material";
import ThumbUp from "@mui/icons-material/ThumbUp";
import LinearProgress from "@mui/material/LinearProgress";
import List from "@mui/material/List";
import ListItemButton from "@mui/material/ListItemButton";
import ListItemIcon from "@mui/material/ListItemIcon";
import { useState } from "react";
import { AvatarBlock, AvatarBlockProps } from "../blocks/avatar/AvatarBlock";
import { HeaderBlock, HeaderBlockProps } from "../blocks/header/HeaderBlock";
import { LinkButtonBlock, LinkButtonBlockProps } from "../blocks/link-button/LinkButtonBlock";
import { SocialNetworksBlock, SocialNetworksBlockProps } from "../blocks/networks/SocialNetworksBlock";
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
            response.order,
            new SocialNetworksBlockProps()
          )
        ))
      });
  }

  const addLinkButton = () => {
    setLoading(true)
    addBlock(state.getCurrentPage(), BlockType.BLOCK_LINK_BUTTON)
      .then(r => {
        dispatch(new BlockAddRequest(
          state.getCurrentPage(),
          new LinkButtonBlock(
            r.id,
            r.order,
            new LinkButtonBlockProps()
          )
        ));
      })
      .finally(() => setLoading(false));
  };

  return (
    <>
      {isLoading && <LinearProgress />}
      <List sx={{
        p: 2
      }}>
        <ListItemButton onClick={addAvatar} disabled={isLoading}>
          <ListItemIcon>
            <AccountCircle />
          </ListItemIcon>
          Avatar
        </ListItemButton>

        <ListItemButton onClick={addHeader} disabled={isLoading}>
          <ListItemIcon>
            <Title />
          </ListItemIcon>
          Header
        </ListItemButton>

        <ListItemButton onClick={addSocialNetworks} disabled={isLoading}>
          <ListItemIcon>
            <ThumbUp />
          </ListItemIcon>
          Social Networks
        </ListItemButton>

        <ListItemButton onClick={addLinkButton} disabled={isLoading}>
          <ListItemIcon>
            <AddLink />
          </ListItemIcon>
          Link Button
        </ListItemButton>
      </List>
    </>
  );
}