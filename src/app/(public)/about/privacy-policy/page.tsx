import { Benefits } from "../../components/Benefits";
import { LegalLinks } from "../../components/LegalLinks";
import { Navigation } from "../../components/Navigation";
import { PageDivider } from "../../components/PageDivider";
import { Subscribe } from "../../components/Subscribe";
import { PrivacyPolicy } from "./components/PrivacyPolicy";

export default function Page() {
  return (
    <>
      <Navigation />
      <PageDivider />

      <PrivacyPolicy />
      <PageDivider />

      <Benefits />
      <PageDivider />

      <Subscribe />
      <PageDivider />

      <LegalLinks />
    </>
  )
}