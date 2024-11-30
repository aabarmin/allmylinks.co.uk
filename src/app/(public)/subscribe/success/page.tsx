import { Container } from "@mui/material";
import Typography from "@mui/material/Typography";
import { Benefits } from "../../components/Benefits";
import { LegalLinks } from "../../components/LegalLinks";
import { Navigation } from "../../components/Navigation";
import { PageDivider } from "../../components/PageDivider";

export default function Page() {
  return (
    <>
      <Navigation />
      <PageDivider />

      <Container>
        <Typography variant="h2">Thank you for subscribing!</Typography>
        <Typography>
          You will receive an email confirmation shortly.
        </Typography>
      </Container>
      <PageDivider />

      <Benefits />
      <PageDivider />

      <LegalLinks />
    </>
  );
}