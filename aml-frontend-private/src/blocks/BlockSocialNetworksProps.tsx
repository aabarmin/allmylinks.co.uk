import { useCallback, useEffect } from 'react';
import { Button, Col, Container, Form, Row, Table } from 'react-bootstrap';
import { Trash } from 'react-bootstrap-icons';
import { useFieldArray, useForm, type SubmitHandler } from 'react-hook-form';
import type { BlockResponse } from '../model/BlockModel';
import type { SocialNetworksBlockProps } from '../model/SocialNetworksBlockProps';
import BlockToolbar, { type ToolbarHandlers } from './BlockToolbar';

interface Props {
  block: BlockResponse,
  handlers: ToolbarHandlers
}

export default function BlockSocialNetworksProps({ block, handlers }: Props) {
  const props: SocialNetworksBlockProps = block.blockProps as SocialNetworksBlockProps;
  const { register, handleSubmit, formState: { errors }, setValue, control } = useForm<SocialNetworksBlockProps>({})
  const { fields, append, remove } = useFieldArray({
    name: "links",
    control
  })
  const onSubmit: SubmitHandler<SocialNetworksBlockProps> = (data) => handlers.onSave(block, data)
  const onAddClick = useCallback(() => {
    append({ url: "", network: "FACEBOOK" });
  }, [append]);
  const onRemoveClick = useCallback((index: number) => {
    remove(index);
  }, [remove]);
  useEffect(() => {
    setValue("links", props.links)
  }, [block]);

  return (
    <Form onSubmit={handleSubmit(onSubmit)} noValidate>
      <BlockToolbar block={block} handlers={handlers} />
      <Container>
        <Row>
          <Col className='d-grid'>
            <Button onClick={() => onAddClick()}>
              Add social network
            </Button>
          </Col>
        </Row>
        <Row>
          <Col>
            <Table>
              <tbody>
                {fields.map((_, index) => (
                  <tr key={index}>
                    <td>
                      <Form.Select {...register(`links.${index}.network`)}>
                        <option value="FACEBOOK">Facebook</option>
                        <option value="TWITTER">Twitter</option>
                        <option value="X">X</option>
                        <option value="Instagram">Instagram</option>
                      </Form.Select>
                    </td>
                    <td>
                      <Form.Control {...register(`links.${index}.url`)} />
                    </td>
                    <td>
                      <Button
                        variant='danger'
                        onClick={() => onRemoveClick(index)}
                      >
                        <Trash />
                      </Button>
                    </td>
                  </tr>
                ))}
              </tbody>
            </Table>
          </Col>
        </Row>
      </Container>
    </Form>
  );
}