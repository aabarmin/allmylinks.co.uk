import { Description } from "@mui/icons-material";
import { Box, Button, Option, Select } from "@mui/joy";
import { useAppState } from "../hooks/StateProvider";

export function PageDetails() {
  const { state } = useAppState();
  const firstPage = state.pages[0].id;

  return (
    <Box sx={{
      p: 2
    }}>
      <div style={{
        display: 'flex',
        gap: 8,
        flexDirection: 'row'
      }}>
        <Select value={firstPage} sx={{
          flexGrow: 1
        }}>
          {state.getPages().map(p => {
            return <Option key={p.id} value={p.id}>{p.title}</Option>
          })}
        </Select>
        <Button endDecorator={<Description />}>
          Manage
        </Button>
      </div>
    </Box>
  )
}