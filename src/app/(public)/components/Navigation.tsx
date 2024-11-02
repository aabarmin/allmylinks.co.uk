import { isLoggedIn } from "@/lib/server/userActions";
import { Adb, Home, Login, Logout, Sell } from "@mui/icons-material";
import { Container, Stack, Typography } from "@mui/material";
import AppBar from "@mui/material/AppBar";
import Toolbar from "@mui/material/Toolbar";
import Link from "next/link";
import { NavigationButton } from "./NavigationButton";

export async function Navigation() {
  const loggedIn = await isLoggedIn();

  return (
    <AppBar position="sticky">
      <Container>
        <Toolbar disableGutters>
          <Adb />
          <Typography
            level="h4"
            noWrap
            sx={{
              mr: 2,
              display: { xs: 'none', md: 'flex' },
              fontFamily: 'monospace',
              fontWeight: 700,
              letterSpacing: '.3rem',
              color: 'inherit',
              textDecoration: 'none',
            }}
          >
            LOGO
          </Typography>

          <Stack direction="row" spacing={2} sx={{ flexGrow: 1 }}>
            <Link href="/" passHref>
              <NavigationButton startIcon={<Home />}>
                Home
              </NavigationButton>
            </Link>
            <Link href="/pricing" passHref>
              <NavigationButton startIcon={<Sell />}>
                Pricing
              </NavigationButton>
            </Link>
          </Stack>
          <Link href="/dashboard" passHref>
            <NavigationButton startIcon={<Login />}>
              {loggedIn ? 'Dashboard' : 'Login'}
            </NavigationButton>
          </Link>
          {loggedIn && (
            <Link href="/logout" passHref>
              <NavigationButton startIcon={<Logout />}>
                Logout
              </NavigationButton>
            </Link>
          )}
        </Toolbar>
      </Container>
    </AppBar>
  );
}