'use client';

import Description from "@mui/icons-material/Description";
import { Tooltip } from "@mui/material";
import Button from "@mui/material/Button";
import MenuItem from "@mui/material/MenuItem";
import Select, { SelectChangeEvent } from "@mui/material/Select";
import Stack from "@mui/material/Stack";
import { PageSelectRequest } from "../hooks/page/PageSelectRequest";
import { LeftPanelChangeRequest } from "../hooks/sidebar/LeftPanelChangeRequest";
import { useAppState } from "../hooks/StateProvider";

export function PageDetails() {
  const { state, dispatch } = useAppState();
  const firstPage = state.getCurrentPage().id;
  const openPagesManagement = () => {
    dispatch(new LeftPanelChangeRequest(
      'page-management'
    ))
  }
  const pageSelect = (event: SelectChangeEvent) => {
    const value = event.target.value as string;
    const page = state.getPage(+value)
    if (page != undefined) {
      dispatch(new PageSelectRequest(page))
    }
  }

  return (
    <Stack direction="row" spacing={2}>
      <Tooltip title="This option is not available yet">
        <Select
          fullWidth
          disabled
          value={firstPage.toString()}
          onChange={pageSelect}
        >
          {state.getPages().map(p => {
            return <MenuItem key={p.id} value={p.id}>{p.title}</MenuItem>
          })}
        </Select>
      </Tooltip>

      <Tooltip title="This option is not available yet">
        <Button
          variant="contained"
          disabled
          sx={{ width: '180px' }}
          startIcon={<Description />}
          onClick={openPagesManagement}>
          Manage
        </Button>
      </Tooltip>
    </Stack>
  )
}