import { Container, Row } from 'react-bootstrap';
import type { BlockResponse } from '../model/BlockModel';
import type { LinkButtonBlockProps } from '../model/LinkButtonBlockProps';

interface Props {
  block: BlockResponse;
}

export default function BlockButton({ block }: Props) {
  const props = block.blockProps as LinkButtonBlockProps;

  return (
    <Container className="preview-pane-block">
      <Row>
        {/* <Col className="d-grid">
          <Button
            href={link}
            target="_blank"
            className={`${size.htmlClass} ${color.htmlClass}`}
            variant="primary"
          >
            {text}
          </Button>
        </Col> */}
      </Row>
    </Container>
  );
}