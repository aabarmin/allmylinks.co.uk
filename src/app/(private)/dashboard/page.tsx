'use client';

import { BlockPropertiesPane } from "@/app/components/BlockPropertiesPane";
import { LeftSidebar } from "@/app/components/LeftSidebar";
import { PreviewPane } from "@/app/components/PreviewPane";
import { DashboardLoadDataRequest } from "@/app/hooks/global/DashboardLoadDataRequest";
import { useAppState } from "@/app/hooks/StateProvider";
import { getDashboard } from "@/lib/client/dashboardActions";
import { Box, LinearProgress } from "@mui/material";
import Grid from "@mui/material/Grid2";
import { useEffect, useState } from "react";

export default function Page() {
  const [loadingState, setLoadingState] = useState<string>('');
  const { dispatch } = useAppState()
  useEffect(() => {
    setLoadingState('loading');
    getDashboard().then(dashboard => {
      setLoadingState('ok');
      dispatch(new DashboardLoadDataRequest(dashboard));
    });
  }, [setLoadingState, dispatch]);

  return (
    <>
      {loadingState == 'loading' && (<LinearProgress />)}
      {
        loadingState == 'ok' && (
          <Grid container>
            <Grid size={4}>
              <Box sx={{ height: '100vh', p: 1, overflow: 'scroll' }}>
                <LeftSidebar />
              </Box>
            </Grid>
            <Grid size={5}>
              <Box sx={{ height: '100vh', p: 1, overflow: 'scroll' }}>
                <PreviewPane />
              </Box>
            </Grid>
            <Grid size={3}>
              <Box sx={{ height: '100vh', p: 1 }}>
                <BlockPropertiesPane />
              </Box>
            </Grid>
          </Grid>
        )
      }
    </>
  );
}
