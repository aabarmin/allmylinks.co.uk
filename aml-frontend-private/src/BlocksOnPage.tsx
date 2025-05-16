import { useCallback } from "react";
import { ListGroup } from "react-bootstrap";
import { getIconByType } from "./BlocksList";
import type { BlockModel } from "./model/BlockModel";
import type { PageModel } from "./model/PageModel";

interface Props {
  currentPage: PageModel;
  onBlockSelected: (block: BlockModel) => void
}

export default function BlocksOnPage({ currentPage, onBlockSelected }: Props) {
  const onBlockSelectedClick = useCallback((block: BlockModel) => {
    onBlockSelected(block);
  }, []);

  return (
    <ListGroup variant="flush">
      {currentPage.pageBlocks.map((block) => (
        <ListGroup.Item
          key={block.blockId}
          action
          onClick={() => onBlockSelectedClick(block)}
        >
          {getIconByType(block.blockType.type)}
          &nbsp;
          {block.blockType.name}
        </ListGroup.Item>
      ))}
    </ListGroup>
  );
}