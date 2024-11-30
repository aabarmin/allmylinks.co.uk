import { Navigation } from "@/app/(public)/components/Navigation"
import { PageDivider } from "@/app/(public)/components/PageDivider"
import { LogoutArea } from "./components/LogoutArea"

export default async function Page() {
  return (
    <>
      <Navigation />
      <PageDivider />

      <LogoutArea />
    </>
  )
}