import { useCallback, useState } from 'react';
import { Accordion, Col, Container, Row } from 'react-bootstrap';
import BlocksList from './BlocksList';
import BlocksOnPage from './BlocksOnPage';
import { useRefresh } from './context/RefreshContext';
import type { BlockTypeModel } from './model/BlockTypeModel';
import type { PageModel } from './model/PageModel';

interface Props {
  availableBlocks: BlockTypeModel[];
  currentPage: PageModel;
}

export default function BlocksAccordion({ availableBlocks, currentPage }: Props) {
  const { refresh } = useRefresh();
  const [active, setActive] = useState("0");
  const onSectionSelect = useCallback((e: string) => {
    setActive(e);
  }, [setActive]);
  const onBlockAdded = useCallback(() => {
    refresh();
  }, []);

  return (
    <Container>
      <Row>
        <Col>
          <Accordion
            defaultActiveKey={active}
            onSelect={(e) => onSectionSelect(e)}
          >
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
