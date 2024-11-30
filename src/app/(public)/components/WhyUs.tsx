import { FlashOn, Redeem, Stream, Tune } from "@mui/icons-material";
import { Container, Stack } from "@mui/material";
import Box from "@mui/material/Box";
import Button from "@mui/material/Button";
import Grid from "@mui/material/Grid2";
import Typography from "@mui/material/Typography";
import Link from "next/link";

function BenefitsSquare() {
  return (
    <Grid container spacing={2}>
      <Grid size={6}>
        <Stack spacing={2} alignItems="center">
          <Tune sx={{
            fontSize: 60,
          }} />
          <Typography variant="h5" textAlign="center">
            More customizable
          </Typography>
          <Typography textAlign="center">
            With powerful customization options and versatile content blocks, your profile will rise above the noise.
          </Typography>
        </Stack>
      </Grid>
      <Grid size={6}>
        <Stack spacing={2} alignItems="center">
          <FlashOn sx={{
            fontSize: 60,
          }} />
          <Typography variant="h5" textAlign="center">
            More powerful
          </Typography>
          <Typography textAlign="center">
            Greater variety of block layouts and more options for organization
          </Typography>
        </Stack>
      </Grid>
      <Grid size={6}>
        <Stack spacing={2} alignItems="center">
          <Redeem sx={{
            fontSize: 60,
          }} />
          <Typography variant="h5" textAlign="center">
            More affordable
          </Typography>
          <Typography textAlign="center">
            We are more affordable than the competition and an excellent choice for teams.
          </Typography>
        </Stack>
      </Grid>
      <Grid size={6}>
        <Stack spacing={2} alignItems="center">
          <Redeem sx={{
            fontSize: 60,
          }} />
          <Typography variant="h5" textAlign="center">
            Live in minutes
          </Typography>
          <Typography textAlign="center">
            Get started in minutes with our easy-to-use interface
          </Typography>
        </Stack>
      </Grid>
    </Grid>
  )
}

export function WhyUs() {
  return (
    <>
      <Container>
        <Grid container>
          <Grid size={6} style={{
            display: 'flex',
            alignItems: 'center'
          }}>
            <Box>
              <Stack spacing={2} sx={{
                p: 2
              }}>
                <Typography variant="h5">
                  Why choose us?
                </Typography>

                <Typography>
                  With powerful customization options and versatile content blocks, your profile will rise above the noise.
                  Plus, we are more affordable than the competition and an excellent choice for teams.
                </Typography>
                <Link href={"/dashboard"} passHref>
                  <Button
                    startIcon={<Stream />}
                    size="large"
                    variant="contained">
                    Get started
                  </Button>
                </Link>
              </Stack>
            </Box>
          </Grid>
          <Grid size={6}>
            <BenefitsSquare />
          </Grid>
        </Grid>
      </Container>
    </>
  );
}