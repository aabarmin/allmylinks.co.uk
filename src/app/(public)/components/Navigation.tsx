import { Adb } from "@mui/icons-material";
import { Button, Container, Stack, Typography } from "@mui/material";
import AppBar from "@mui/material/AppBar";
import Toolbar from "@mui/material/Toolbar";
import Link from "next/link";

export function Navigation() {
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

          <Stack direction="row" spacing={2}>
            <Link href="/" passHref>
              <Button>Home</Button>
            </Link>
            <Link href="/customers" passHref>
              <Button>Customers</Button>
            </Link>
            <Link href="/help" passHref>
              <Button>Help</Button>
            </Link>
            <Link href="/dashboard" passHref>
              <Button>Dashboard</Button>
            </Link>
          </Stack>
        </Toolbar>
      </Container>
    </AppBar>
  );
}