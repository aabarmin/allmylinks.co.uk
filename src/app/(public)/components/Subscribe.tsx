import { ForwardToInbox } from "@mui/icons-material";
import { Container, Stack } from "@mui/material";
import Button from "@mui/material/Button";
import Grid from "@mui/material/Grid2";
import TextField from "@mui/material/TextField";
import Typography from "@mui/material/Typography";

export function Subscribe() {
  return (
    <Container>
      <Grid container>
        <Grid size={8} offset={2}>
          <Stack spacing={2}>
            <Typography
              variant="h2"
              textAlign="center">
              Subscribe
            </Typography>
            <Typography>
              Subscribe to our newsletter to stay up to date with our latest news
            </Typography>
            <Stack
              direction="row"
              spacing={2}>
              <TextField label="Email" variant="outlined" fullWidth />
              <Button
                sx={{
                  width: '260px'
                }}
                startIcon={<ForwardToInbox />}
                variant="contained"
                color="primary">
                Subscribe
              </Button>
            </Stack>
          </Stack>
        </Grid>
      </Grid>
    </Container>
  );
}