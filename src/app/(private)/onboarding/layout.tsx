import "@/app/globals.css";
import { getCurrentUser } from "@/lib/server/userActions";
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

  return (
    <>
      {children}
    </>
  );
}
