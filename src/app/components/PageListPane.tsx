import { ArrowBack, Delete } from "@mui/icons-material";
import { Box, Button, Input, List, ListItem, ListItemButton, ListItemContent, Sheet, Typography } from "@mui/joy";
import { LeftPanelChangeRequest } from "../hooks/sidebar/LeftPanelChangeRequest";
import { useAppState } from "../hooks/StateProvider";

export function PageListPane() {
  const { state, dispatch } = useAppState();
  const navigateBack = () => dispatch(new LeftPanelChangeRequest('page-builder'))
  const pages = state.getPages();

  return (
    <Box>
      <Sheet>
        <Button startDecorator={<ArrowBack />} onClick={navigateBack}>
          Back
        </Button>
      </Sheet>
      <Sheet>
        <Typography level="h1">Manage pages</Typography>
        <Typography>
          Add pages to your profile by clicking the 'Add page' button. Add link to pages by using 'Link page' block.
        </Typography>
      </Sheet>
      <Sheet sx={{ height: '16px' }} />
      <List>
        {pages.map(p => {
          return (
            <ListItem key={p.id}>
              <ListItemContent>
                <Input value={p.title} />
              </ListItemContent>
              <ListItemButton>
                <Delete />
              </ListItemButton>
            </ListItem>
          )
        })}
        <ListItem key='add-new'>
          <ListItemContent>
            <Button>Add new page</Button>
          </ListItemContent>
        </ListItem>
      </List>



    </Box>
  )
}