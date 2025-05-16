import { useCallback } from 'react';
import { Accordion, Col, Container, Row } from 'react-bootstrap';
import BlocksList from './BlocksList';
import type { BlockTypeModel } from './model/BlockTypeModel';
import type { PageModel } from './model/PageModel';

interface Props {
    availableBlocks: BlockTypeModel[];
    currentPage: PageModel
    // pageBlocks: any[];
    // currentPageId: string;
}

export default function BlocksAccordion({ availableBlocks, currentPage }: Props) {
    const onBlockAdded = useCallback(() => {
        console.log("Block added")
    }, []);

    return (
        <Container>
            <Row>
                <Col>
                    <Accordion defaultActiveKey="0" id="blocks-accordion">
                        <Accordion.Item eventKey="0">
                            <Accordion.Header>Blocks</Accordion.Header>
                            <Accordion.Body>
                                <BlocksList
                                    availableBlocks={availableBlocks}
                                    currentPage={currentPage}
                                    onBlockAdded={onBlockAdded}
                                />
                            </Accordion.Body>
                        </Accordion.Item>
                        <Accordion.Item eventKey="1">
                            <Accordion.Header>Page Blocks</Accordion.Header>
                            <Accordion.Body>
                                {/* <BlocksOnPage pageBlocks={pageBlocks} currentPageId={currentPageId} /> */}
                            </Accordion.Body>
                        </Accordion.Item>
                    </Accordion>
                </Col>
            </Row>
        </Container>
    );
}
