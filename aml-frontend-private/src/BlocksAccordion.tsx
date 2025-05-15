import { Accordion, Container, Row, Col } from 'react-bootstrap';
import BlocksList from './BlocksList';
import BlocksOnPage from './BlocksOnPage';

interface BlocksAccordionProps {
    availableBlocks: any[]; // Replace `any` with the appropriate type for blocks
    pageBlocks: any[]; // Replace `any` with the appropriate type for page blocks
    currentPageId: string;
}

export default function BlocksAccordion({ availableBlocks, pageBlocks, currentPageId }: BlocksAccordionProps) {
    return (
        <Container>
            <Row>
                <Col>
                    <Accordion defaultActiveKey="0" id="blocks-accordion">
                        <Accordion.Item eventKey="0">
                            <Accordion.Header>Blocks</Accordion.Header>
                            <Accordion.Body>
                                <BlocksList availableBlocks={availableBlocks} currentPageId={currentPageId} />
                            </Accordion.Body>
                        </Accordion.Item>
                        <Accordion.Item eventKey="1">
                            <Accordion.Header>Page Blocks</Accordion.Header>
                            <Accordion.Body>
                                <BlocksOnPage pageBlocks={pageBlocks} currentPageId={currentPageId} />
                            </Accordion.Body>
                        </Accordion.Item>
                    </Accordion>
                </Col>
            </Row>
        </Container>
    );
}