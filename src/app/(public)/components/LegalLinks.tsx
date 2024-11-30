import AppBar from "@mui/material/AppBar";
import Toolbar from "@mui/material/Toolbar";
import Link from "next/link";
import { NavigationButton } from "./NavigationButton";


export function LegalLinks() {
  return (
    <AppBar position="static">
      <Toolbar>
        <Link href="/about/privacy-policy" passHref>
          <NavigationButton>
            Privacy policy
          </NavigationButton>
        </Link>

        <Link href="/about/terms-of-service" passHref>
          <NavigationButton>
            Terms of Service
          </NavigationButton>
        </Link>

        <Link href="/about/cookie-policy" passHref>
          <NavigationButton>
            Cookie Policy
          </NavigationButton>
        </Link>
      </Toolbar>
    </AppBar>
  )
}