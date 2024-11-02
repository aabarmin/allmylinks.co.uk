import List from "@mui/material/List";
import ListItem from "@mui/material/ListItem";
import ListItemButton from "@mui/material/ListItemButton";
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