import { useLoaderData } from "react-router";
import BlockHeaderProps from "./blocks/BlockHeaderProps";
import type { ToolbarHandlers } from "./blocks/BlockToolbar";
import type { BlockResponse } from "./model/BlockModel";

export default function BlockProperties() {
  const block: BlockResponse = useLoaderData();
  const handlers: ToolbarHandlers = {
    onSave: () => { debugger },
    onDelete: () => { debugger },
    onMoveUp: () => { debugger },
    onMoveDown: () => { debugger },
  }

  switch (block.blockType.type) {
    case "HEADER_BLOCK": return <BlockHeaderProps block={block} handlers={handlers} />
    default: return <div>Config for {block.blockType.name}</div>
  }
}