import Container from "@mui/material/Container";
import Grid from "@mui/material/Grid2";
import Typography from "@mui/material/Typography";
import { PageDivider } from "../../components/PageDivider";
import { FreePlan } from "./FreePlan";
import { PremiumPlan } from "./PremiumPlan";

export function Pricing() {
  return (
    <Container>
      <Typography variant="h2">Plans and pricing</Typography>
      <Typography>
        Subscribe now to get access to free features and premium content blocks.
      </Typography>

      <PageDivider />

      <Grid container>
        <Grid size={4} offset={1}>
          <FreePlan />
        </Grid>
        <Grid size={4} offset={2}>
          <PremiumPlan />
        </Grid>
      </Grid>
    </Container>
  )
}