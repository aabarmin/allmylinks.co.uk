'use client';

import { Menu } from "@mui/icons-material";
import { Box, Drawer, List, ListItem, ListItemButton, Option, Select } from "@mui/joy";
import { useState } from "react";
import { useAppState } from "../hooks/StateProvider";

export default function Navigation() {
  const [menuOpened, setMenuOpened] = useState<boolean>(false);
  const { state } = useAppState();
  const firstPage = state.getPages()[0].id;

  return (
    <Box>
      <List orientation="horizontal">
        <ListItem>
          <ListItemButton onClick={() => setMenuOpened(true)}>
            <Menu />
          </ListItemButton>
        </ListItem>
        <ListItem>
          Page:
          <Select value={firstPage}>
            {state.pages.map(p => <Option value={p.id} key={p.id}>{p.title}</Option>)}
          </Select>
        </ListItem>
      </List>

      <Drawer open={menuOpened} onClose={() => setMenuOpened(false)}>
        <Box onClick={() => setMenuOpened(false)}>
          <List>
            <ListItem>
              <ListItemButton>
                Some actions goes here
              </ListItemButton>
            </ListItem>
          </List>
        </Box>
      </Drawer>
    </Box>
  );
}