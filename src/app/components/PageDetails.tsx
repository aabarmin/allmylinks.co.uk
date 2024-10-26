import { getCurrentProfile, getPages } from "@/lib/profileActions";
import { getCurrentUserId } from "@/lib/userActions";
import { Description } from "@mui/icons-material";
import { Box, Button, Option, Select } from "@mui/joy";

export async function PageDetails({ params }: { params: { id?: number } }) {
  const currentUser = await getCurrentUserId();
  const currentProfile = await getCurrentProfile(currentUser);
  const pages = await getPages(currentProfile);
  const currentPageId = pages
    .filter(p => p.isHome)
    .map(p => p.id)[0]

  if (params?.id) {
    console.error('Not implemented yet!')
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
        <Select value={currentPageId}>
          {pages.map(p => {
            return <Option key={p.id} value={p.id}>{p.title}</Option>
          })}
        </Select>
      </Box>
      <Button endDecorator={<Description />}>
        Manage
      </Button>
    </Box>
  )
}