import { ServicePromoImage } from "@/app/(public)/components/ServicePromoImage";
import { Container, Stack } from "@mui/material";
import Grid from "@mui/material/Grid2";
import Typography from "@mui/material/Typography";
import { OnboardingForm } from "./OnboardingForm";

function OnboardingIntroText() {
  return (
    <>
      <Typography variant="h2">
        Let us know you a bit more
      </Typography>
      <Typography>
        Please fill the form belowe to complete the onboarding process.
        This will help us to know you better and create your public profile.
      </Typography>
    </>
  )
}

export async function OnboardingPane() {
  return (
    <Container>
      <Grid container>
        <Grid size={6}>
          <Stack spacing={2} p={2}>
            <OnboardingIntroText />
            <OnboardingForm />
          </Stack>
        </Grid>
        <Grid size={4} offset={2}>
          <ServicePromoImage />
        </Grid>
      </Grid>
    </Container>
  )
}