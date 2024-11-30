import { LegalLinks } from "@/app/(public)/components/LegalLinks";
import { Navigation } from "@/app/(public)/components/Navigation";
import { PageDivider } from "@/app/(public)/components/PageDivider";
import { isOnboardingCompleted } from "@/lib/server/onboardingActions";
import { getCurrentUser, isLoggedIn } from "@/lib/server/userActions";
import { redirect } from "next/navigation";
import { OnboardingPane } from "./component/OnboardingPage";

export default async function Page() {
  const loggedIn = await isLoggedIn();
  if (!loggedIn) {
    redirect('/login')
  }
  const currentUser = await getCurrentUser();
  if (!currentUser) {
    redirect('/login')
  }
  const onboardingCompleted = await isOnboardingCompleted(currentUser);
  if (onboardingCompleted) {
    redirect('/dashboard')
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