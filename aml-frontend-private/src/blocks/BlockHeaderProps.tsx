import { Col, Container, Form, Row } from 'react-bootstrap';
import { useForm, type SubmitHandler } from 'react-hook-form';
import type { BlockResponse } from '../model/BlockModel';
import type { HeaderBlockProps } from '../model/HeaderBlockProps';
import BlockToolbar, { type ToolbarHandlers } from './BlockToolbar';

interface Props {
  block: BlockResponse,
  handlers: ToolbarHandlers
}

export default function BlockHeaderProps({ block, handlers }: Props) {
  const props: HeaderBlockProps = block.blockProps as HeaderBlockProps;
  const { register, handleSubmit, formState: { errors } } = useForm<HeaderBlockProps>({ defaultValues: props })
  const onSubmit: SubmitHandler<HeaderBlockProps> = (data) => handlers.onSave(block, data)

  return (
    <Form onSubmit={handleSubmit(onSubmit)} noValidate>
      <BlockToolbar block={block} handlers={handlers} />
      <Container>
        <Col>
          <Row>
            <Form.Group>
              <Form.Label>Level:</Form.Label>
              <Form.Select {...register("level")}>
                <option value="H1">Level 1</option>
                <option value="H2">Level 2</option>
                <option value="H3">Level 3</option>
              </Form.Select>
            </Form.Group>
          </Row>
          <Row>
            <Form.Group>
              <Form.Label>Alignment:</Form.Label>
              <Form.Select {...register("alignment")}>
                <option value="LEFT">Left</option>
                <option value="CENTER">Center</option>
                <option value="RIGHT">Right</option>
              </Form.Select>
            </Form.Group>
          </Row>
          <Row>
            <Form.Group>
              <Form.Label>Text:</Form.Label>
              <Form.Control {...register("text", { required: "Text is required" })} />
              <Form.Control.Feedback>{errors.text?.message}</Form.Control.Feedback>
            </Form.Group>
          </Row>
        </Col>
      </Container>
    </Form>
  );
}