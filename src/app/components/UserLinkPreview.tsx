import { getCurrentProfile } from "@/lib/profileActions";
import { getCurrentUserId } from "@/lib/userActions";
import { ContentCopy, Share } from "@mui/icons-material";
import { Box, IconButton } from "@mui/joy";

export async function UserLinkPreview() {
  const userId = await getCurrentUserId();
  const profile = await getCurrentProfile(userId)
  const profileLink = process.env.BASE_URL + "/l/" + profile.link;
  const profileLinkText = profileLink.substring(profileLink.indexOf("://") + 3)

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
        <a href={profileLink} target="_blank">
          {profileLinkText}
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