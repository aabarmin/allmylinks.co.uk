import Box from "@mui/material/Box";
import type { Metadata } from "next";
import "./globals.css";

export const metadata: Metadata = {
  title: "All My Links",
  description: "Get all your links in one place",
};

export default function RootLayout({
  children,
}: Readonly<{
  children: React.ReactNode;
}>) {
  return (
    <html lang="en">
      <body>
        <Box sx={{ height: '100vh' }}>
          {children}
        </Box>
      </body>
    </html>
  );
}
