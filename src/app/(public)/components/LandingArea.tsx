import { Container, Grid } from "@mui/material";

export function LandingArea() {
  return (
    <Container>
      <Grid container>
        <Grid xs={6}>
          <h1>Landing Area</h1>
        </Grid>
        <Grid xs={6}>
          Some image goes here
        </Grid>
      </Grid>
    </Container>
  );
}