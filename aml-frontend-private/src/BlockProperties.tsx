import { useLoaderData } from "react-router";
import BlockHeaderProps from "./blocks/BlockHeaderProps";
import type { ToolbarHandlers } from "./blocks/BlockToolbar";
import { useRefresh } from "./context/RefreshContext";
import type { BlockResponse } from "./model/BlockModel";
import { blockDelete, blockMoveDown, blockMoveUp, blockUpdateProperties } from "./service/BlockService";

export default function BlockProperties() {
  const block: BlockResponse = useLoaderData();
  const { refresh } = useRefresh();
  const handlers: ToolbarHandlers = {
    onSave: (block, props) => {
      blockUpdateProperties(block, props).then(() => {
        refresh();
      })
    },
    onDelete: (block) => {
      blockDelete(block).then(() => {
        refresh();
      });
    },
    onMoveUp: (block) => {
      blockMoveUp(block).then(() => {
        refresh();
      });
    },
    onMoveDown: (block) => {
      blockMoveDown(block).then(() => {
        refresh();
      });
    },
  }

  switch (block.blockType.type) {
    case "HEADER_BLOCK": return <BlockHeaderProps block={block} handlers={handlers} />
    default: return <div>Config for {block.blockType.name}</div>
  }
}