import { Description } from "@mui/icons-material";
import { Box, Button, Option, Select } from "@mui/joy";
import { PageSelectRequest } from "../hooks/page/PageSelectRequest";
import { LeftPanelChangeRequest } from "../hooks/sidebar/LeftPanelChangeRequest";
import { useAppState } from "../hooks/StateProvider";

export function PageDetails() {
  const { state, dispatch } = useAppState();
  const firstPage = state.getCurrentPage().id
  const openPagesManagement = () => {
    dispatch(new LeftPanelChangeRequest(
      'page-management'
    ))
  }
  const pageSelect = (event: any, newValue: number | null) => {
    if (newValue == undefined) {
      return
    }
    const page = state.getPage(newValue)
    if (page != undefined) {
      dispatch(new PageSelectRequest(page))
    }
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
        <Select
          value={firstPage}
          onChange={pageSelect}
        >
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