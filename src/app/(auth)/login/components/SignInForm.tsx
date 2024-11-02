import { ServicePromoImage } from "@/app/(public)/components/ServicePromoImage"
import { signIn } from "@/auth"
import { Container } from "@mui/material"
import Button from "@mui/material/Button"
import Grid from "@mui/material/Grid2"
import Stack from "@mui/material/Stack"

export function SignInForm() {
  return (
    <Container>
      <Grid container>
        <Grid size={4}>
          <Stack spacing={2}>
            <h1>Sign In</h1>
            <p>
              Welcome back! Sign in to your account to continue.
            </p>
            <Stack spacing={2}>
              <form action={async () => {
                'use server'
                await signIn('google', { redirectTo: '/dashboard' })
              }}>
                <Button
                  sx={{ width: '100%' }}
                  variant="contained"
                  type="submit">
                  Sign in with Google
                </Button>
              </form>
              <form action={async () => {
                'use server'
                await signIn('github', { redirectTo: '/dashboard' })
              }}>
                <Button
                  sx={{ width: '100%' }}
                  variant="contained"
                  type="submit">
                  Sign in with GitHub
                </Button>
              </form>
            </Stack>
          </Stack>
        </Grid>
        <Grid size={6} offset={2}>
          <ServicePromoImage />
        </Grid>
      </Grid>
    </Container >
  )
}