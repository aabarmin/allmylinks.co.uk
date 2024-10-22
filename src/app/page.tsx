'use client';

import { Accordion, AccordionDetails, AccordionGroup, AccordionSummary, Box, Sheet } from "@mui/joy";
import Grid from "@mui/joy/Grid";
import { useState } from "react";
import { BlockPropertiesPane } from "./components/BlockPropertiesPane";
import { Blocks } from "./components/Blocks";
import { PageBlockList } from "./components/PageBlockList";
import { PageDetails } from "./components/PageDetails";
import { PreviewPane } from "./components/PreviewPane";
import { UserLinkPreview } from "./components/UserLinkPreview";
import { StateProvider } from "./hooks/StateProvider";


export default function Home() {
  const [opened, setOpened] = useState<string[]>(['page-blocks', 'blocks', 'current-page']);
  const isOpened = (section: string): boolean => {
    return opened.indexOf(section) != -1;
  }
  const toggleOpened = (section: string) => {
    if (isOpened(section)) {
      setOpened(opened.filter(i => i != section));
    } else {
      setOpened([...opened, section])
    }
  }

  return (
    <StateProvider>

      <Grid container spacing={3}>
        <Grid xs={3}>
          <Sheet sx={{ height: '92vh', p: 2, overflow: 'scroll' }}>
            <UserLinkPreview />
            <Box>
              <AccordionGroup variant="outlined">
                <Accordion expanded={isOpened('current-page')} onChange={() => toggleOpened('current-page')}>
                  <AccordionSummary>Page details</AccordionSummary>
                  <AccordionDetails>
                    <PageDetails />
                  </AccordionDetails>
                </Accordion>
                <Accordion expanded={isOpened('blocks')} onChange={() => toggleOpened('blocks')}>
                  <AccordionSummary>Blocks</AccordionSummary>
                  <AccordionDetails>
                    <Blocks />
                  </AccordionDetails>
                </Accordion>
                <Accordion expanded={isOpened('page-blocks')} onChange={() => toggleOpened('page-blocks')}>
                  <AccordionSummary>Page blocks</AccordionSummary>
                  <AccordionDetails>
                    <PageBlockList />
                  </AccordionDetails>
                </Accordion>
              </AccordionGroup>
            </Box>
          </Sheet>
        </Grid>
        <Grid xs={6}>
          <Box sx={{ height: '92vh', p: 2, overflow: 'scroll' }}>
            <PreviewPane />
          </Box>
        </Grid>
        <Grid xs={3}>
          <Sheet sx={{ height: '92vh', p: 2 }}>
            <BlockPropertiesPane />
          </Sheet>
        </Grid>
      </Grid>

    </StateProvider>
  );
}
