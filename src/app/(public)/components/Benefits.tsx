import { FlashOn, MoreTime, Redeem, Tune } from "@mui/icons-material";
import { Stack, Typography } from "@mui/material";
import Container from "@mui/material/Container";
import Grid from "@mui/material/Grid2";

export function Benefits() {
  return (
    <Container>
      <Grid container>
        <Grid size={3}>
          <Stack spacing={2} alignItems="center">
            <MoreTime sx={{
              fontSize: 60,
            }} />

            <Typography level="h4">
              Live in minutes
            </Typography>
          </Stack>
        </Grid>
        <Grid size={3}>
          <Stack spacing={2} alignItems="center">
            <FlashOn sx={{
              fontSize: 60,
            }} />

            <Typography level="h4">
              Powerful block builder
            </Typography>
          </Stack>
        </Grid>
        <Grid size={3}>
          <Stack spacing={2} alignItems="center">
            <Tune sx={{
              fontSize: 60,
            }} />

            <Typography level="h4">
              Fully customizable
            </Typography>
          </Stack>
        </Grid>
        <Grid size={3}>
          <Stack spacing={2} alignItems="center">
            <Redeem sx={{
              fontSize: 60,
            }} />

            <Typography level="h4">
              Affordable
            </Typography>
          </Stack>
        </Grid>
      </Grid>
    </Container>
  )
}