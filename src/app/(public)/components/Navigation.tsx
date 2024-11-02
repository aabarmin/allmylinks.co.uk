import { isLoggedIn } from "@/lib/server/userActions";
import { Adb, Home, Login, Logout, Sell } from "@mui/icons-material";
import AppBar from "@mui/material/AppBar";
import Container from "@mui/material/Container";
import Stack from "@mui/material/Stack";
import Toolbar from "@mui/material/Toolbar";
import Typography from "@mui/material/Typography";
import Link from "next/link";
import { NavigationButton } from "./NavigationButton";

export async function Navigation() {
  const loggedIn = await isLoggedIn();

  return (
    <AppBar position="static">
      <Container>
        <Toolbar disableGutters>
          <Adb />
          <Typography
            variant="h4"
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