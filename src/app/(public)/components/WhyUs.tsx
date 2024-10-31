import { Container, Stack } from "@mui/material";
import Box from "@mui/material/Box";
import Button from "@mui/material/Button";
import Grid from "@mui/material/Grid2";
import Image from "next/image";
import Link from "next/link";

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
                <h1>
                  Why choose us?
                </h1>
                <p>
                  With powerful customization options and versatile content blocks, your profile will rise above the noise.
                  Plus, we're more affordable than the competition and an excellent choice for teams.
                </p>
                <Link href={"/dashboard"} passHref>
                  <Button variant="contained">
                    Get started
                  </Button>
                </Link>
              </Stack>
            </Box>
          </Grid>
          <Grid size={6}>

            <Grid container spacing={2}>
              <Grid size={6}>
                <Stack spacing={2} sx={{
                  display: 'flex',
                  justifyContent: 'center'
                }}>
                  <Box sx={{
                    display: 'flex',
                    justifyContent: 'center'
                  }}>
                    <Image
                      alt="Benefits"
                      width={100}
                      height={100}
                      src="/home/benefits.jpg" />
                  </Box>
                  <h2>More customizable</h2>
                  <p>
                    With powerful customization options and versatile content blocks, your profile will rise above the noise.
                  </p>
                </Stack>
              </Grid>
              <Grid size={6}>
                <Stack spacing={2}>
                  <Box sx={{
                    display: 'flex',
                    justifyContent: 'center'
                  }}>
                    <Image
                      alt="Benefits"
                      width={100}
                      height={100}
                      src="/home/benefits.jpg" />
                  </Box>
                  <h2>More powerful</h2>
                  <p>
                    Greater variety of block layouts and more options for organization
                  </p>
                </Stack>
              </Grid>
              <Grid size={6}>
                <Stack spacing={2}>
                  <Box sx={{
                    display: 'flex',
                    justifyContent: 'center'
                  }}>
                    <Image
                      alt="Benefits"
                      width={100}
                      height={100}
                      src="/home/benefits.jpg" />
                  </Box>
                  <h2>More affordable</h2>
                  <p>
                    We're more affordable than the competition and an excellent choice for teams.
                  </p>
                </Stack>
              </Grid>
              <Grid size={6}>
                <Stack spacing={2}>
                  <Box sx={{
                    display: 'flex',
                    justifyContent: 'center'
                  }}>
                    <Image
                      alt="Benefits"
                      width={100}
                      height={100}
                      src="/home/benefits.jpg" />
                  </Box>
                  <h2>Live in minutes</h2>
                  <p>
                    Get started in minutes with our easy-to-use interface
                  </p>
                </Stack>
              </Grid>
            </Grid>

          </Grid>
        </Grid>
      </Container>
    </>
  );
}