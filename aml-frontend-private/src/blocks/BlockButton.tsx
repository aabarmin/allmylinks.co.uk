import { Col, Container, Row } from 'react-bootstrap';
import type { BlockResponse } from '../model/BlockModel';
import type { LinkButtonBlockProps, LinkButtonColor, LinkButtonSize } from '../model/LinkButtonBlockProps';

interface Props {
  block: BlockResponse;
}

function sizeToClass(size: LinkButtonSize): string {
  switch (size) {
    case "LARGE": return "btn-lg";
    case "NORMAL": return "";
    case "SMALL": return "btn-sm";
  }
}

function colorToClass(color: LinkButtonColor): string {
  switch (color) {
    case "BLUE": return "btn-primary";
    case "GRAY": return "btn-secondary";
    case "GREEN": return "btn-success";
    case "RED": return "btn-danger";
    case "YELLOW": return "btn-warning";
    case "LIGHT_BLUE": return "btn-info";
    case "LIGHT_GREY": return "btn-light";
    case "DARK": return "btn-dark";
    case "REGULAR": return "btn-link";
  }
}

export default function BlockButton({ block }: Props) {
  const props = block.blockProps as LinkButtonBlockProps;
  const className = ['btn', 'btn-primary', sizeToClass(props.size), colorToClass(props.color)].join(' ');

  return (
    <Container className="preview-pane-block">
      <Row>
        <Col className='d-grid'>
          <a
            href={props.link}
            target='_blank'
            className={className}
          >
            {props.text}
          </a>
        </Col>
      </Row>
    </Container>
  );
}