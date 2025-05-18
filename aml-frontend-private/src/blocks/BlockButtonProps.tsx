import { useEffect } from 'react';
import { Col, Container, Form, Row } from 'react-bootstrap';
import { useForm, type SubmitHandler } from 'react-hook-form';
import type { BlockResponse } from '../model/BlockModel';
import type { LinkButtonBlockProps } from '../model/LinkButtonBlockProps';
import BlockToolbar, { type ToolbarHandlers } from './BlockToolbar';

interface Props {
  block: BlockResponse,
  handlers: ToolbarHandlers
}

export default function BlockButtonProps({ block, handlers }: Props) {
  const props: LinkButtonBlockProps = block.blockProps as LinkButtonBlockProps;
  const { register, handleSubmit, formState: { errors }, setValue } = useForm<LinkButtonBlockProps>({})
  const onSubmit: SubmitHandler<LinkButtonBlockProps> = (data) => handlers.onSave(block, data)
  useEffect(() => {
    setValue("text", props.text);
    setValue("link", props.link);
    setValue("size", props.size);
    setValue("color", props.color);
  }, [block, setValue]);

  return (
    <Form onSubmit={handleSubmit(onSubmit)} noValidate>
      <BlockToolbar block={block} handlers={handlers} />
      <Container>
        <Col>
          <Row>
            <Form.Group>
              <Form.Label>Text:</Form.Label>
              <Form.Control {...register("text", { required: "Text is required" })} />
            </Form.Group>
          </Row>
          <Row>
            <Form.Group>
              <Form.Label>Link:</Form.Label>
              <Form.Control {...register("link", { required: "Link is required" })} />
            </Form.Group>
          </Row>
          <Row>
            <Form.Group>
              <Form.Label>Size:</Form.Label>
              <Form.Select {...register("size")}>
                <option value="NORMAL">Normal</option>
                <option value="SMALL">Small</option>
                <option value="LARGE">Large</option>
              </Form.Select>
            </Form.Group>
          </Row>
          <Row>
            <Form.Group>
              <Form.Label>Color:</Form.Label>
              <Form.Select {...register("color")}>
                <option value="BLUE">Blue</option>
                <option value="GRAY">Gray</option>
                <option value="GREEN">Green</option>
                <option value="RED">Red</option>
                <option value="YELLOW">Yellow</option>
                <option value="LIGHT_BLUE">Light Blue</option>
                <option value="LIGHT_GREY">Light Grey</option>
                <option value="DARK">Dark</option>
                <option value="REGULAR">Regular</option>
              </Form.Select>
            </Form.Group>
          </Row>
        </Col>
      </Container>
    </Form>
  );
}