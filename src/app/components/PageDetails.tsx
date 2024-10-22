import { Description } from "@mui/icons-material";
import { Box, Button, Option, Select } from "@mui/joy";
import { LeftPanelChangeRequest } from "../hooks/sidebar/LeftPanelChangeRequest";
import { useAppState } from "../hooks/StateProvider";

export function PageDetails() {
  const { state, dispatch } = useAppState();
  const firstPage = state.getPages()[0].id;
  const openPagesManagement = () => {
    dispatch(new LeftPanelChangeRequest(
      'page-management'
    ))
  }

  return (
    <Box sx={{
      p: 2,
      display: 'flex',
      gap: 2
    }}>
      <Box sx={{
        flexGrow: 1
      }}>
        <Select value={firstPage}>
          {state.getPages().map(p => {
            return <Option key={p.id} value={p.id}>{p.title}</Option>
          })}
        </Select>
      </Box>
      <Button endDecorator={<Description />} onClick={openPagesManagement}>
        Manage
      </Button>
    </Box>
  )
}