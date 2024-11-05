import "@/app/globals.css";
import { StateProvider } from "@/app/hooks/StateProvider";
import { isOnboardingCompleted } from "@/lib/server/onboardingActions";
import { getCurrentUser } from "@/lib/server/userActions";
import Box from "@mui/material/Box";
import type { Metadata } from "next";
import { redirect } from "next/navigation";

export const metadata: Metadata = {
  title: "All My Links",
  description: "Get all your links in one place",
};

export default async function RootLayout({
  children,
}: Readonly<{
  children: React.ReactNode;
}>) {
  const user = await getCurrentUser();
  if (!user) {
    redirect('/login')
  }
  const onboardingCompleted = await isOnboardingCompleted(user);
  if (!onboardingCompleted) {
    redirect('/onboarding');
  }

  return (
    <Box sx={{ height: '100vh' }}>
      <StateProvider>
        {children}
      </StateProvider>
    </Box>
  );
}
