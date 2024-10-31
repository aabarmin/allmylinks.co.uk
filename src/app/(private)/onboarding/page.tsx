import { getCurrentUser } from "@/lib/userActions";
import { Box, Grid } from "@mui/joy";
import { redirect } from "next/navigation";
import { OnboardingForm } from "./component/OnboardingForm";

export default async function Page() {
  const user = await getCurrentUser();
  if (!user) {
    redirect('/login')
  }

  return (
    <Grid container>
      <Grid xs={6}>
        <Box sx={{
          p: 2,
          border: '1px solid black',
        }}>
          <h1>Welcome to the onboarding page</h1>
          <p>Here you will be able to complete the onboarding process</p>

          <p>I'm not good at creativity today, need to be refactored later on</p>

          <OnboardingForm />
        </Box>
      </Grid>
      <Grid xs={6}>
        <Box sx={{
          p: 2,
        }}>
          Some fancy onboarding image
        </Box>
      </Grid>
    </Grid>
  );
}