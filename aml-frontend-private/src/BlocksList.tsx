import { useCallback, type ReactNode } from "react";
import { ListGroup } from "react-bootstrap";
import { ExclamationDiamondFill, Fonts, HandThumbsUp, Link, PersonCircle } from "react-bootstrap-icons";
import type { BlockTypeModel } from "./model/BlockTypeModel";
import type { PageModel } from "./model/PageModel";
import { addBlock } from "./service/BlockService";

interface Props {
  availableBlocks: BlockTypeModel[];
  currentPage: PageModel;
  onBlockAdded: () => void;
}

function getIconByType(type: string): ReactNode {
  switch (type) {
    case 'AVATAR_BLOCK': return <PersonCircle />
    case 'BUTTON_BLOCK': return <Link />
    case 'HEADER_BLOCK': return <Fonts />
    case 'SOCIAL_NETWORKS_BLOCK': return <HandThumbsUp />
    default: return <ExclamationDiamondFill />
  }
}

export default function BlocksList({ availableBlocks, currentPage, onBlockAdded }: Props) {
  const onBlockAddClick = useCallback((block: BlockTypeModel) => {
    addBlock(block, currentPage).then(() => {
      onBlockAdded();
    });
  }, []);

  return (
    <ListGroup variant="flush">
      {availableBlocks.map((block) =>
        <ListGroup.Item
          key={block.type}
          action
          onClick={() => onBlockAddClick(block)}
        >
          {getIconByType(block.type)}
          &nbsp;
          {block.name}
        </ListGroup.Item>
      )}
    </ListGroup>
  );
}

