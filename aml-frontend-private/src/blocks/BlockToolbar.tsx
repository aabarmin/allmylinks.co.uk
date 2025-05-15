import React from 'react';
import { Button, Dropdown, Row, Col } from 'react-bootstrap';

interface BlockToolbarProps {
    block: {
        pageId: string;
        blockId: string;
        canMoveUp: boolean;
        canMoveDown: boolean;
    };
}

export default function BlockToolbar({ block }: BlockToolbarProps) {
    return (
        <>
            <Row>
                <Col>
                    <a href="/private/dashboard" className="btn btn-outline-primary">
                        Back
                    </a>
                    <Button type="submit" variant="primary" className="ms-2">
                        Save
                    </Button>
                </Col>
                <Col className="text-end">
                    <Dropdown>
                        <Dropdown.Toggle variant="secondary" id="dropdown-basic">
                            <i className="bi bi-gear"></i>
                        </Dropdown.Toggle>

                        <Dropdown.Menu>
                            <Dropdown.Item
                                href={`/private/dashboard/${block.pageId}/blocks/${block.blockId}/up`}
                                className={!block.canMoveUp ? 'disabled' : ''}
                                disabled={!block.canMoveUp}
                            >
                                <i className="bi bi-arrow-up"></i> Move up
                            </Dropdown.Item>
                            <Dropdown.Item
                                href={`/private/dashboard/${block.pageId}/blocks/${block.blockId}/down`}
                                className={!block.canMoveDown ? 'disabled' : ''}
                                disabled={!block.canMoveDown}
                            >
                                <i className="bi bi-arrow-down"></i> Move down
                            </Dropdown.Item>
                            <Dropdown.Item
                                href={`/private/dashboard/${block.pageId}/blocks/${block.blockId}/delete`}
                            >
                                <i className="bi bi-trash"></i> Delete
                            </Dropdown.Item>
                        </Dropdown.Menu>
                    </Dropdown>
                </Col>
            </Row>
            <Row>
                <Col>&nbsp;</Col>
            </Row>
        </>
    );
}