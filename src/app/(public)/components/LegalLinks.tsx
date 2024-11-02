import AppBar from "@mui/material/AppBar";
import Toolbar from "@mui/material/Toolbar";
import { NavigationButton } from "./NavigationButton";


export function LegalLinks() {
  return (
    <AppBar position="static">
      <Toolbar>
        <NavigationButton>
          Privacy policy
        </NavigationButton>
        <NavigationButton>
          Terms of Service
        </NavigationButton>
        <NavigationButton>
          Cookie Policy
        </NavigationButton>
      </Toolbar>
    </AppBar>
  )
}