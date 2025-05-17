import { Col, Container, Row } from 'react-bootstrap';
import type { PageModel } from './model/PageModel';
import PreviewPane from './PreviewPane';

interface Props {
  currentPage: PageModel
}

export default function DashboardPreview({ currentPage }: Props) {
  return (
    <Container>
      <Row>
        <Col className="preview-pane-container">
          <PreviewPane
            pageBlocks={currentPage.pageBlocks}
            pageProps={currentPage.pageProps}
          />
        </Col>
      </Row>
    </Container>
  );
}