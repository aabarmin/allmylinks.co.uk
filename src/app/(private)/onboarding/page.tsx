import { LegalLinks } from "@/app/(public)/components/LegalLinks";
import { Navigation } from "@/app/(public)/components/Navigation";
import { PageDivider } from "@/app/(public)/components/PageDivider";
import { isLoggedIn } from "@/lib/server/userActions";
import { redirect } from "next/navigation";
import { OnboardingPane } from "./component/OnboardingPage";

export default async function Page() {
  const loggedIn = await isLoggedIn();
  if (!loggedIn) {
    redirect('/login')
  }

  return (
    <>
      <Navigation />
      <PageDivider />

      <OnboardingPane />
      <PageDivider />

      <LegalLinks />
    </>
  );
}