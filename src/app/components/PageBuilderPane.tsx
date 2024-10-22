import { Accordion, AccordionDetails, AccordionGroup, AccordionSummary, Box } from "@mui/joy";
import { useState } from "react";
import { Blocks } from "./Blocks";
import { PageBlockList } from "./PageBlockList";
import { PageDetails } from "./PageDetails";
import { UserLinkPreview } from "./UserLinkPreview";

export function PageBuilderPane() {
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
    <Box>
      <UserLinkPreview />
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
  )
}