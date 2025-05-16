import { useCallback } from 'react';
import { Accordion, Col, Container, Row } from 'react-bootstrap';
import BlocksList from './BlocksList';
import BlocksOnPage from './BlocksOnPage';
import type { BlockTypeModel } from './model/BlockTypeModel';
import type { PageModel } from './model/PageModel';

interface Props {
    availableBlocks: BlockTypeModel[];
    currentPage: PageModel;
    onModelChanged: () => void
}

export default function BlocksAccordion({ availableBlocks, currentPage, onModelChanged }: Props) {
    const onBlockAdded = useCallback(() => {
        onModelChanged();
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
                                <BlocksOnPage
                                    currentPage={currentPage}
                                />
                            </Accordion.Body>
                        </Accordion.Item>
                    </Accordion>
                </Col>
            </Row>
        </Container>
    );
}
