'use client';

import { ExpandMore } from "@mui/icons-material";
import Accordion from "@mui/material/Accordion";
import AccordionDetails from "@mui/material/AccordionDetails";
import AccordionSummary from "@mui/material/AccordionSummary";
import Box from "@mui/material/Box";
import { UserLinkPreview } from "../(private)/dashboard/component/UserLinkPreview";
import { Blocks } from "./Blocks";
import { PageBlockList } from "./PageBlockList";

export function PageBuilderPane() {
  return (
    <Box>
      <UserLinkPreview />
      {/* <Accordion>
        <AccordionSummary expandIcon={<ExpandMore />}>
          Page details
        </AccordionSummary>
        <AccordionDetails>
          <PageDetails />
        </AccordionDetails>
      </Accordion> */}
      <Accordion defaultExpanded>
        <AccordionSummary expandIcon={<ExpandMore />}>
          Blocks
        </AccordionSummary>
        <AccordionDetails>
          <Blocks />
        </AccordionDetails>
      </Accordion>
      <Accordion defaultExpanded>
        <AccordionSummary expandIcon={<ExpandMore />}>
          Page blocks
        </AccordionSummary>
        <AccordionDetails>
          <PageBlockList />
        </AccordionDetails>
      </Accordion>
    </Box>
  )
}