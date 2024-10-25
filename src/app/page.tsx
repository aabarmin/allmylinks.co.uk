'use client';

import { Box } from "@mui/joy";
import Link from "next/link";

export default function Home() {
  return (
    <Box>
      <Link href={"/dashboard"}>
        To the dashboard
      </Link>
    </Box>
  );
}
