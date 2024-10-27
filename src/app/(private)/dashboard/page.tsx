'use client';

import { DashboardResponse } from "@/app/(api)/api/dashboard/DashboardResponse";
import { BlockPropertiesPane } from "@/app/components/BlockPropertiesPane";
import { LeftSidebar } from "@/app/components/LeftSidebar";
import { PreviewPane } from "@/app/components/PreviewPane";
import { DashboardLoadDataRequest } from "@/app/hooks/global/DashboardLoadDataRequest";
import { useAppState } from "@/app/hooks/StateProvider";
import { Box, Sheet } from "@mui/joy";
import Grid from "@mui/joy/Grid";
import { useEffect, useState } from "react";

export default function Page() {
  const [loadingState, setLoadingState] = useState<string>('');
  const { dispatch } = useAppState()
  useEffect(() => {
    setLoadingState('loading');
    fetch('/api/dashboard')
      .then((response) => {
        if (response.status == 401) {
          setLoadingState('unauthorized');
          return
        }
        if (response.status == 500) {
          setLoadingState('error');
          return
        }
        response.json().then((data: DashboardResponse) => {
          dispatch(new DashboardLoadDataRequest(data));
          setLoadingState('ok');
        });
      })
  }, [setLoadingState, dispatch]);

  return (
    <>
      {loadingState == 'loading' && <div>Loading...</div>}
      {
        loadingState == 'ok' && (
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
        )
      }
    </>
  );
}
