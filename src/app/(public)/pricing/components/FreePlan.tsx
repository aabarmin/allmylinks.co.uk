import { AccountCircle } from "@mui/icons-material";
import ThumbUp from "@mui/icons-material/ThumbUp";
import Title from "@mui/icons-material/Title";
import { Card, CardContent, List, ListItem } from "@mui/material";
import Button from "@mui/material/Button";
import Typography from "@mui/material/Typography";
import Link from "next/link";

export function FreePlan() {
  return (
    <Card>
      <CardContent>
        <Typography variant="h4">Free</Typography>
        <Typography>Get access to all the basic features for free</Typography>

        <Typography>Features:</Typography>
        <List>
          <ListItem>
            <AccountCircle />
            Avatar content block
          </ListItem>

          <ListItem>
            <Title />
            Header block
          </ListItem>

          <ListItem>
            <ThumbUp />
            Social Networks
          </ListItem>
        </List>

        <Link href="/dashboard">
          <Button
            variant="contained"
            fullWidth
            size="large">
            Get started for free
          </Button>
        </Link>
      </CardContent>
    </Card>
  )
}