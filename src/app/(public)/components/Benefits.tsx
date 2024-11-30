import { FlashOn, MoreTime, Redeem, Tune } from "@mui/icons-material";
import Container from "@mui/material/Container";
import Grid from "@mui/material/Grid2";
import Stack from "@mui/material/Stack";
import Typography from "@mui/material/Typography";

export function Benefits() {
  return (
    <Container>
      <Grid container>
        <Grid size={3}>
          <Stack spacing={2} alignItems="center">
            <MoreTime sx={{
              fontSize: 60,
            }} />

            <Typography variant="h5">
              Live in minutes
            </Typography>
          </Stack>
        </Grid>
        <Grid size={3}>
          <Stack spacing={2} alignItems="center">
            <FlashOn sx={{
              fontSize: 60,
            }} />

            <Typography variant="h5">
              Powerful block builder
            </Typography>
          </Stack>
        </Grid>
        <Grid size={3}>
          <Stack spacing={2} alignItems="center">
            <Tune sx={{
              fontSize: 60,
            }} />

            <Typography variant="h5">
              Fully customizable
            </Typography>
          </Stack>
        </Grid>
        <Grid size={3}>
          <Stack spacing={2} alignItems="center">
            <Redeem sx={{
              fontSize: 60,
            }} />

            <Typography variant="h5">
              Affordable
            </Typography>
          </Stack>
        </Grid>
      </Grid>
    </Container>
  )
}