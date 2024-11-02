
import { LegalLinks } from "@/app/(public)/components/LegalLinks"
import { Navigation } from "@/app/(public)/components/Navigation"
import { PageDivider } from "@/app/(public)/components/PageDivider"
import { Subscribe } from "@/app/(public)/components/Subscribe"
import { SignInForm } from "./components/SignInForm"

export default function SignIn() {
  return (
    <>
      <Navigation />
      <PageDivider />
      <SignInForm />
      <PageDivider />
      <Subscribe />
      <PageDivider />
      <LegalLinks />
    </>
  )
} 