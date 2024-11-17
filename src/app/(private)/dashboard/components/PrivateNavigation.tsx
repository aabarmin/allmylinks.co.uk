import { NavigationButton } from "@/app/(public)/components/NavigationButton";
import { Home, Login, Logout, ManageAccounts } from "@mui/icons-material";
import AppBar from "@mui/material/AppBar";
import Container from "@mui/material/Container";
import Stack from "@mui/material/Stack";
import Toolbar from "@mui/material/Toolbar";
import Link from "next/link";

export function PrivateNavigation() {
  return (
    <AppBar position="sticky">
      <Container>
        <Toolbar disableGutters>
          <Stack direction="row" spacing={2} sx={{ flexGrow: 1 }}>
            <Link href="/" passHref>
              <NavigationButton startIcon={<Home />}>
                Home
              </NavigationButton>
            </Link>
            <Link href="/dashboard" passHref>
              <NavigationButton startIcon={<Login />}>
                Dashboard
              </NavigationButton>
            </Link>
            <Link href="/dashboard/profile" passHref>
              <NavigationButton startIcon={<ManageAccounts />}>
                Profile
              </NavigationButton>
            </Link>
          </Stack>
          <Link href="/logout" passHref>
            <NavigationButton startIcon={<Logout />}>
              Logout
            </NavigationButton>
          </Link>
        </Toolbar>
      </Container>
    </AppBar>
  )
}