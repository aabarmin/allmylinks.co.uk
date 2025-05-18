import { Button, Col, Container, Dropdown, Row } from 'react-bootstrap';
import { ArrowDown, ArrowUp, Gear, Trash } from 'react-bootstrap-icons';
import { NavLink } from 'react-router';
import type { BlockPropsTypes, BlockResponse } from '../model/BlockModel';

export interface ToolbarHandlers {
  onSave: (block: BlockResponse, props: BlockPropsTypes) => void;
  onMoveUp: (block: BlockResponse) => void;
  onMoveDown: (block: BlockResponse) => void;
  onDelete: (block: BlockResponse) => void;
}

interface Props {
  block: BlockResponse;
  handlers: ToolbarHandlers;
}

export default function BlockToolbar({ block, handlers }: Props) {
  return (
    <Container>
      <Row>
        <Col>
          <NavLink
            to={`/pages/${block.pageId}`}
            className='btn btn-outline-primary'
          >
            Back
          </NavLink>
          &nbsp;
          <Button
            type='submit'
            variant='outline-primary'
          >
            Save
          </Button>
        </Col>
        <Col className="text-end">
          <Dropdown>
            <Dropdown.Toggle variant="secondary" id="dropdown-basic">
              <Gear />
            </Dropdown.Toggle>

            <Dropdown.Menu>
              <Dropdown.Item
                as={Button}
                onClick={() => handlers.onMoveUp(block)}
                className={!block.canMoveUp ? 'disabled' : ''}
                disabled={!block.canMoveUp}
              >
                <ArrowUp />
                &nbsp;
                Move up
              </Dropdown.Item>
              <Dropdown.Item
                as={Button}
                onClick={() => handlers.onMoveDown(block)}
                className={!block.canMoveDown ? 'disabled' : ''}
                disabled={!block.canMoveDown}
              >
                <ArrowDown />
                &nbsp;
                Move down
              </Dropdown.Item>
              <Dropdown.Item
                as={Button}
                onClick={() => handlers.onDelete(block)}
              >
                <Trash />
                &nbsp;
                Delete
              </Dropdown.Item>
            </Dropdown.Menu>
          </Dropdown>
        </Col>
      </Row>
      <Row>
        <Col>&nbsp;</Col>
      </Row>
    </Container>
  );
}