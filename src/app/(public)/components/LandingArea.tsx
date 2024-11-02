import { Box, Container, Stack, Typography } from "@mui/material";
import Button from "@mui/material/Button";
import Grid from "@mui/material/Grid2";
import TextField from "@mui/material/TextField";
import { ServicePromoImage } from "./ServicePromoImage";

export function LandingArea() {
  return (
    <>
      <Container>
        <Grid container>
          <Grid size={6}>
            <Box sx={{
              display: 'flex',
              alignItems: 'center',
              height: '500px',
            }}>
              <Stack spacing={2}>
                <h1>Centralize your online presence</h1>
                <p>
                  Bring together your socials, music, videos, and more on a stunning, interactive link-in-bio page.
                  Claim your name now and make your bio stand out!
                </p>
                <Stack direction="row" spacing={2}>
                  <TextField variant="outlined" label="Your name" fullWidth />
                  <Button variant="contained" style={{
                    width: '180px'
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
      <Container>
        <Grid container>
          <Grid size={3}>
            <Typography level="h4">Live in minutes</Typography>
          </Grid>
          <Grid size={3}>
            <Typography level="h4">Powerful block builder</Typography>
          </Grid>
          <Grid size={3}>
            <Typography level="h4">Fully customizable</Typography>
          </Grid>
          <Grid size={3}>
            <Typography level="h4">Affordable</Typography>
          </Grid>
        </Grid>
      </Container>
    </>
  );
}