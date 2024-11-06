import { Benefits } from "../../components/Benefits";
import { LegalLinks } from "../../components/LegalLinks";
import { Navigation } from "../../components/Navigation";
import { PageDivider } from "../../components/PageDivider";
import { Subscribe } from "../../components/Subscribe";
import { CookiePolicy } from "./components/CookiePolicy";

export default function Page() {
  return (
    <>
      <Navigation />
      <PageDivider />

      <CookiePolicy />
      <PageDivider />

      <Benefits />
      <PageDivider />

      <Subscribe />
      <PageDivider />

      <LegalLinks />
    </>
  )
}