import { useAppState } from "@/app/hooks/StateProvider";
import { ContentCopy, Share } from "@mui/icons-material";
import { Box, IconButton } from "@mui/joy";

export function UserLinkPreview() {
  const { state } = useAppState();
  const profile = state.getProfile();

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
        <a href={profile.profileLink} target="_blank">
          {profile.profileDisplayLink}
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