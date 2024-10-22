import { ContentCopy, Share } from "@mui/icons-material";
import { Box, IconButton } from "@mui/joy";

export function UserLinkPreview() {
  return (
    <Box sx={{
      p: 2,
      display: 'flex',
      alignItems: 'center'
    }}>
      <div style={{
        flexGrow: 1,
        display: 'flex',
        alignItems: 'center'
      }}>
        <a href="https://google.com" target="_blank">
          google.com
        </a>
        <IconButton>
          <ContentCopy />
        </IconButton>
      </div>
      <IconButton>
        <Share />
      </IconButton>
    </Box>
  );
}