import { signOut } from "@/auth";
import { Container, Stack } from "@mui/material";
import Button from "@mui/material/Button";
import Typography from "@mui/material/Typography";
import Link from "next/link";

export function LogoutArea() {
  return (
    <Container>
      <Stack spacing={2}>
        <Typography variant="h2">
          Logging out?
        </Typography>

        <Typography>
          Are you sure you want to log out?
        </Typography>

        <Stack spacing={2} direction="row">
          <Link href="/dashboard" passHref>
            <Button variant="contained">
              Back to the dashboard
            </Button>
          </Link>

          <form
            action={async () => {
              "use server"
              await signOut({ redirectTo: '/' })
            }}
          >
            <Button
              type="submit"
              variant="outlined">
              Sign out
            </Button>
          </form>
        </Stack>
      </Stack>
    </Container>
  )
}