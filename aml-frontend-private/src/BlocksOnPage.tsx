import { ListGroup } from "react-bootstrap";
import { NavLink } from "react-router";
import { getIconByType } from "./BlocksList";
import type { PageModel } from "./model/PageModel";

interface Props {
  currentPage: PageModel;
}

export default function BlocksOnPage({ currentPage }: Props) {
  return (
    <ListGroup variant="flush">
      {currentPage.pageBlocks.map((block) => (
        <NavLink
          key={block.blockId}
          to={`/pages/${block.pageId}/blocks/${block.blockId}`}
          className="list-group-item"
        >
          {getIconByType(block.blockType.type)}
          &nbsp;
          {block.blockType.name}
        </NavLink>
      ))}
    </ListGroup>
  );
}