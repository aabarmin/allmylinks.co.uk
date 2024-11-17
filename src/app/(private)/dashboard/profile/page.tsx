import { PageDivider } from "@/app/(public)/components/PageDivider";
import { Container, Typography } from "@mui/material";
import Grid from "@mui/material/Grid2";
import DangerCard from "./components/DangerCard";
import EmailCard from "./components/EmailCard";
import ProfileCard from "./components/ProfileCard";

export default function Page() {
  return (
    <>
      <PageDivider />
      <Container>
        <Typography variant="h4">Profile</Typography>

        <PageDivider />
        <Grid container spacing={2}>
          <Grid size={6}>
            <EmailCard />
          </Grid>

          <Grid size={6}>
            <ProfileCard />
          </Grid>

          <Grid size={6}>
            <DangerCard />
          </Grid>
        </Grid>
      </Container>
    </>
  );
}