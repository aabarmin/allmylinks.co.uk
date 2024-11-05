import "@/app/globals.css";
import Box from "@mui/material/Box";
import type { Metadata } from "next";

export const metadata: Metadata = {
  title: "All My Links",
  description: "Get all your links in one place",
};

export default async function RootLayout({
  children,
}: Readonly<{
  children: React.ReactNode;
}>) {

  return (
    <Box sx={{ height: '100vh' }}>
      {children}
    </Box>
  );
}
