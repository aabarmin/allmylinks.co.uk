import { LandingArea } from "./components/LandingArea";
import { LegalLinks } from "./components/LegalLinks";
import { Navigation } from "./components/Navigation";
import { PageDivider } from "./components/PageDivider";
import { Subscribe } from "./components/Subscribe";
import { WhyUs } from "./components/WhyUs";

export function HomePage() {
  return (
    <>
      <Navigation />
      <PageDivider />

      <LandingArea />
      <PageDivider />

      <WhyUs />
      <PageDivider />

      <Subscribe />
      <PageDivider />

      <LegalLinks />
    </>
  );
}