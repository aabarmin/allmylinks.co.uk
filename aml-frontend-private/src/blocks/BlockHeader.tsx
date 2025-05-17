import { type ReactNode } from 'react';
import { Col, Container, Row } from 'react-bootstrap';
import type { BlockResponse } from '../model/BlockModel';
import type { HeaderBlockProps } from '../model/HeaderBlockProps';

interface Props {
  block: BlockResponse
}

function getAlignmentClass(props: HeaderBlockProps): string {
  switch (props.alignment) {
    case "LEFT": return "text-start";
    case "CENTER": return "text-center";
    case "RIGHT": return "text-end";
  }
}

function renderBlock(props: HeaderBlockProps): ReactNode {
  const className = getAlignmentClass(props)

  switch (props.level) {
    case "H1": return <h1 className={className}>{props.text}</h1>
    case "H2": return <h2 className={className}>{props.text}</h2>
    case "H3": return <h3 className={className}>{props.text}</h3>
  }
}

export default function BlockHeader({ block }: Props) {
  const props: HeaderBlockProps = block.blockProps as HeaderBlockProps;

  return (
    <Container className="preview-pane-block">
      <Row>
        <Col>{renderBlock(props)}</Col>
      </Row>
    </Container>
  );
}