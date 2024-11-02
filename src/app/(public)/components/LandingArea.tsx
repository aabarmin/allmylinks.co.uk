import { Stream } from "@mui/icons-material";
import { Box, Container, Stack } from "@mui/material";
import Button from "@mui/material/Button";
import Grid from "@mui/material/Grid2";
import TextField from "@mui/material/TextField";
import Typography from "@mui/material/Typography";
import { ServicePromoImage } from "./ServicePromoImage";

export function LandingArea() {
  return (
    <Container>
      <Grid container>
        <Grid size={6}>
          <Box sx={{
            display: 'flex',
            alignItems: 'center',
            height: '500px',
          }}>
            <Stack spacing={2}>
              <Typography variant="h2">
                Centralize your online presence
              </Typography>
              <Typography>
                Bring together your socials, music, videos, and more on a stunning, interactive link-in-bio page.
                Claim your name now and make your bio stand out!
              </Typography>
              <Stack direction="row" spacing={2}>
                <TextField variant="outlined" label="Your name" fullWidth />
                <Button
                  startIcon={<Stream />}
                  variant="contained"
                  style={{
                    width: '260px'
                  }}>
                  Get started
                </Button>
              </Stack>
            </Stack>
          </Box>
        </Grid>
        <Grid size={6}>
          <Box sx={{
            display: 'flex',
            justifyContent: 'center',
          }}>
            <ServicePromoImage />
          </Box>
        </Grid>
      </Grid>
    </Container>
  );
}