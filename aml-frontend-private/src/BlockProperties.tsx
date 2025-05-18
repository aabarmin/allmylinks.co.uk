import { useEffect, useState } from "react";
import { useLoaderData } from "react-router";
import BlockButtonProps from "./blocks/BlockButtonProps";
import BlockHeaderProps from "./blocks/BlockHeaderProps";
import BlockSocialNetworksProps from "./blocks/BlockSocialNetworksProps";
import type { ToolbarHandlers } from "./blocks/BlockToolbar";
import { useRefresh } from "./context/RefreshContext";
import { BlockResponse } from "./model/BlockModel";
import { blockDelete, blockGet, blockMoveDown, blockMoveUp, blockUpdateProperties } from "./service/BlockService";

export default function BlockProperties() {
  const blockId: number = useLoaderData();
  const [block, setBlock] = useState<BlockResponse | undefined>(undefined);
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
  useEffect(() => {
    blockGet(blockId).then(b => setBlock(b));
  }, [blockId]);

  if (!block) {
    return (<div></div>);
  }

  switch (block.blockType.type) {
    case "HEADER_BLOCK": return <BlockHeaderProps block={block} handlers={handlers} />
    case "BUTTON_BLOCK": return <BlockButtonProps block={block} handlers={handlers} />
    case "SOCIAL_NETWORKS_BLOCK": return <BlockSocialNetworksProps block={block} handlers={handlers} />
    default: return <div>Config for {block.blockType.name}</div>
  }
}