'use client';

import { BlockPropertiesPane } from "@/app/components/BlockPropertiesPane";
import { LeftSidebar } from "@/app/components/LeftSidebar";
import { PreviewPane } from "@/app/components/PreviewPane";
import { DashboardLoadDataRequest } from "@/app/hooks/global/DashboardLoadDataRequest";
import { useAppState } from "@/app/hooks/StateProvider";
import { getDashboard } from "@/lib/client/dashboardActions";
import { Box, Sheet } from "@mui/joy";
import Grid from "@mui/joy/Grid";
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
      {loadingState == 'loading' && <div>Loading...</div>}
      {
        loadingState == 'ok' && (
          <Grid container>
            {/* <Grid xs={1}>
              <Sheet sx={{ height: '100vh', p: 1 }}>
                <UserNavigation />
              </Sheet>
            </Grid> */}
            <Grid xs={4}>
              <Sheet sx={{ height: '100vh', p: 1 }}>
                <LeftSidebar />
              </Sheet>
            </Grid>
            <Grid xs={5}>
              <Box sx={{ height: '100vh', p: 1, overflow: 'scroll' }}>
                <PreviewPane />
              </Box>
            </Grid>
            <Grid xs={3}>
              <Sheet sx={{ height: '100vh', p: 1 }}>
                <BlockPropertiesPane />
              </Sheet>
            </Grid>
          </Grid>
        )
      }
    </>
  );
}
