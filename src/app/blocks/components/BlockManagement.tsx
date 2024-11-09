import { ApplicationState } from "@/app/hooks/ApplicationState";
import { BlockDeleteRequest } from "@/app/hooks/block/BlockDeleteRequest";
import { BlockMoveDownRequest } from "@/app/hooks/block/BlockMoveDownRequest";
import { BlockMoveUpRequest } from "@/app/hooks/block/BlockMoveUpRequest";
import { useAppState } from "@/app/hooks/StateProvider";
import { Block } from "@/app/model/Block";
import { deleteBlock, moveDownBlock, moveUpBlock } from "@/lib/client/blockActions";
import ArrowDownward from "@mui/icons-material/ArrowDownward";
import ArrowUpward from "@mui/icons-material/ArrowUpward";
import Delete from "@mui/icons-material/Delete";
import Settings from "@mui/icons-material/Settings";
import IconButton from "@mui/material/IconButton";
import ListItemIcon from "@mui/material/ListItemIcon";
import Menu from "@mui/material/Menu";
import MenuItem from "@mui/material/MenuItem";
import { useState } from "react";

export type BlockManagementProps = {
  block: Block<object>;
  disabled?: boolean;
  setLoading: (isLoading: boolean) => void;
}

function isFirstBlock(block: Block<object>): boolean {
  return block.order == 1;
}

function isLastBlock(block: Block<object>, state: ApplicationState): boolean {
  const page = state.getCurrentPage();
  const blocks = page.blocks;
  if (blocks.length == 0) {
    return true;
  }
  return blocks[blocks.length - 1].id === block.id;
}

export function BlockManagement({ block, disabled, setLoading }: BlockManagementProps) {
  const { state, dispatch } = useAppState();
  const [anchorEl, setAnchorEl] = useState<null | HTMLElement>(null);
  const menuOpen = Boolean(anchorEl);
  const onMenuClick = (event: React.MouseEvent<HTMLElement>) => {
    setAnchorEl(event.currentTarget);
  };
  const onMenuClose = () => {
    setAnchorEl(null);
  };
  const onMoveUp = () => {
    setLoading(true);
    onMenuClose();
    moveUpBlock(block)
      .then(() => {
        dispatch(new BlockMoveUpRequest(state.getCurrentPage(), block));
      })
      .finally(() => setLoading(false));
  }
  const onMoveDown = () => {
    setLoading(true);
    onMenuClose();
    moveDownBlock(block)
      .then(() => {
        dispatch(new BlockMoveDownRequest(state.getCurrentPage(), block));
      })
      .finally(() => setLoading(false));
  }
  const onDelete = () => {
    setLoading(true);
    onMenuClose();
    deleteBlock(block)
      .then(() => {
        dispatch(new BlockDeleteRequest(state.getCurrentPage(), block));
      })
      .finally(() => setLoading(false));
  }

  return (
    <>
      <IconButton
        disabled={disabled}
        onClick={onMenuClick}>
        <Settings />
      </IconButton>

      <Menu
        open={menuOpen}
        onClose={onMenuClose}
        anchorEl={anchorEl}>
        <MenuItem
          disabled={isFirstBlock(block)}
          onClick={onMoveUp}>
          <ListItemIcon>
            <ArrowUpward />
          </ListItemIcon>
          Move up
        </MenuItem>
        <MenuItem
          disabled={isLastBlock(block, state)}
          onClick={onMoveDown}>
          <ListItemIcon>
            <ArrowDownward />
          </ListItemIcon>
          Move down
        </MenuItem>
        <MenuItem
          onClick={onDelete}>
          <ListItemIcon>
            <Delete />
          </ListItemIcon>
          Delete
        </MenuItem>
      </Menu>
    </>
  );

}