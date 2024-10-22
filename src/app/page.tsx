'use client';

import { Box, Sheet } from "@mui/joy";
import Grid from "@mui/joy/Grid";
import { BlockPropertiesPane } from "./components/BlockPropertiesPane";
import { LeftSidebar } from "./components/LeftSidebar";
import { PreviewPane } from "./components/PreviewPane";
import { StateProvider } from "./hooks/StateProvider";

export default function Home() {
  return (
    <StateProvider>

      <Grid container spacing={3}>
        <Grid xs={3}>
          <Sheet sx={{ height: '100vh', p: 2, overflow: 'scroll' }}>
            <LeftSidebar />
          </Sheet>
        </Grid>
        <Grid xs={6}>
          <Box sx={{ height: '100vh', p: 2, overflow: 'scroll' }}>
            <PreviewPane />
          </Box>
        </Grid>
        <Grid xs={3}>
          <Sheet sx={{ height: '100vh', p: 2 }}>
            <BlockPropertiesPane />
          </Sheet>
        </Grid>
      </Grid>

    </StateProvider>
  );
}
