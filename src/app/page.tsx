'use client';

import { Accordion, AccordionDetails, AccordionGroup, AccordionSummary, Box, Sheet } from "@mui/joy";
import Grid from "@mui/joy/Grid";
import { BlockPropertiesPane } from "./components/BlockPropertiesPane";
import Blocks from "./components/Blocks";
import PreviewPane from "./components/PreviewPane";
import { StateProvider } from "./hooks/StateProvider";


export default function Home() {
  return (
    <StateProvider>

      <Grid container spacing={3}>
        <Grid xs={3}>
          <Box>
            <Sheet variant="soft">
              Бокс со ссылкой на страницу
              <a href="https://allabout.me/@tester" target="_blank">
                allabout.me/@tester
              </a>
            </Sheet>
          </Box>
          <Box>
            <AccordionGroup>
              <Accordion expanded={true}>
                <AccordionSummary>Blocks</AccordionSummary>
                <AccordionDetails>
                  <Blocks />
                </AccordionDetails>
              </Accordion>
            </AccordionGroup>
          </Box>
        </Grid>
        <Grid xs={6}>
          <PreviewPane />
        </Grid>
        <Grid xs={3}>
          <BlockPropertiesPane />
        </Grid>
      </Grid>

    </StateProvider>
  );
}
