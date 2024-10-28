'use client';

import { blocksToReactNodes } from "@/lib/blockUtils";
import { Box } from "@mui/joy";
import { useAppState } from "../hooks/StateProvider";
import MobileFrame from "./MobileFrame";

export default function PreviewMobile() {
  const { state } = useAppState();
  const blocks = state.getCurrentPage().blocks;
  const nodes = blocksToReactNodes(blocks);

  return (
    <Box sx={{
      p: 2,
      display: 'flex',
      justifyContent: 'center'
    }}>
      <MobileFrame blocks={nodes} />
    </Box>
  );
}