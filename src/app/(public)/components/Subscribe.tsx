import { Button, Container, Stack, Typography } from "@mui/material";
import Grid from "@mui/material/Grid2";
import TextField from "@mui/material/TextField";

export function Subscribe() {
  return (
    <Container>
      <Grid container>
        <Grid size={8} offset={2}>
          <Stack spacing={2}>
            <Typography level="h2">Subscribe</Typography>
            <p>
              Subscribe to our newsletter to stay up to date with our latest news
            </p>
            <TextField label="Email" variant="outlined" />
            <Button>Subscribe</Button>
          </Stack>
        </Grid>
      </Grid>
    </Container>
  );
}