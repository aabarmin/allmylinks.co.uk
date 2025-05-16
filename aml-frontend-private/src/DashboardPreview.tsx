import { Col, Container, Row } from 'react-bootstrap';
import type { PageModel } from './model/PageModel';

interface Props {
  currentPage: PageModel
}

export default function DashboardPreview({ currentPage }: Props) {
  return (
    <Container>
      <Row>
        <Col className="preview-pane-container">
          Goes here
          {/* <PreviewPane pageProps={pageProps} pageBlocks={pageBlocks} /> */}
        </Col>
      </Row>
    </Container>
  );
}