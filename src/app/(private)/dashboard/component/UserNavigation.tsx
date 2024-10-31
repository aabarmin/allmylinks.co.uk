import { List, ListDivider, ListItem, ListItemButton } from "@mui/joy";
import Link from "next/link";

export function UserNavigation() {
  return (
    <List>
      <ListItem>
        <Link href="/dashboard">
          <ListItemButton>
            Dashboard
          </ListItemButton>
        </Link>
      </ListItem>
      <ListDivider />
      <ListItem>
        <Link href="/logout">
          <ListItemButton>
            Logout
          </ListItemButton>
        </Link>
      </ListItem>
    </List>
  )
}