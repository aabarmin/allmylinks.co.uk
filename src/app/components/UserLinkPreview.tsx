import { ContentCopy, Share } from "@mui/icons-material";
import { Box, IconButton } from "@mui/joy";

export function UserLinkPreview() {
  return (
    <Box sx={{
      p: 2,
      gap: 2,
      display: 'flex',
      alignItems: 'center'
    }}>
      <Box sx={{
        display: 'flex',
        flexGrow: 1,
        alignItems: 'center',
        gap: 2
      }}>
        <a href="https://google.com" target="_blank">
          google.com
        </a>
        <IconButton>
          <ContentCopy />
        </IconButton>
      </Box>
      <IconButton>
        <Share />
      </IconButton>
    </Box>
  );
}