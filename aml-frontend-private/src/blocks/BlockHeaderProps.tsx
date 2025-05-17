import type { BlockResponse } from '../model/BlockModel';
import BlockToolbar, { type ToolbarHandlers } from './BlockToolbar';

interface Props {
  block: BlockResponse,
  handlers: ToolbarHandlers
}

export default function BlockHeaderProps({ block, handlers }: Props) {
  return (
    <>
      <BlockToolbar block={block} handlers={handlers} />
    </>
  );

  // return (
  //   <Form
  //     action={`/private/dashboard/${pageId}/blocks/${blockId}`}
  //     method="post"
  //   >
  //     <Form.Control type="hidden" name="type" value={blockType.type} />

  //     <Container>
  //       {/* Block Toolbar */}
  //       <BlockToolbar block={currentBlock} />

  //       <Row>
  //         <Col>
  //           {/* Level Dropdown */}
  //           <Form.Group className="mb-3">
  //             <Form.Label htmlFor="headerLevel">Level:</Form.Label>
  //             <Form.Select
  //               id="headerLevel"
  //               name="level"
  //               defaultValue={blockProps.level}
  //               isInvalid={!!errors?.level}
  //             >
  //               <option value="H1">Level 1</option>
  //               <option value="H2">Level 2</option>
  //               <option value="H3">Level 3</option>
  //             </Form.Select>
  //             <Form.Control.Feedback type="invalid">
  //               {errors?.level}
  //             </Form.Control.Feedback>
  //           </Form.Group>

  //           {/* Alignment Radio Buttons */}
  //           <Form.Group className="mb-3">
  //             <Form.Label>Alignment:</Form.Label>
  //             <ButtonGroup>
  //               <ToggleButton
  //                 type="radio"
  //                 id="alignmentLeft"
  //                 name="alignment"
  //                 value="LEFT"
  //                 variant="outline-primary"
  //                 defaultChecked={blockProps.alignment === 'LEFT'}
  //                 isInvalid={!!errors?.alignment}
  //               >
  //                 Left
  //               </ToggleButton>
  //               <ToggleButton
  //                 type="radio"
  //                 id="alignmentCenter"
  //                 name="alignment"
  //                 value="CENTER"
  //                 variant="outline-primary"
  //                 defaultChecked={blockProps.alignment === 'CENTER'}
  //                 isInvalid={!!errors?.alignment}
  //               >
  //                 Center
  //               </ToggleButton>
  //               <ToggleButton
  //                 type="radio"
  //                 id="alignmentRight"
  //                 name="alignment"
  //                 value="RIGHT"
  //                 variant="outline-primary"
  //                 defaultChecked={blockProps.alignment === 'RIGHT'}
  //                 isInvalid={!!errors?.alignment}
  //               >
  //                 Right
  //               </ToggleButton>
  //             </ButtonGroup>
  //             <Form.Control.Feedback type="invalid">
  //               {errors?.alignment}
  //             </Form.Control.Feedback>
  //           </Form.Group>

  //           {/* Text Field */}
  //           <Form.Group className="mb-3">
  //             <Form.Label htmlFor="headerText">Text:</Form.Label>
  //             <Form.Control
  //               id="headerText"
  //               name="text"
  //               type="text"
  //               defaultValue={blockProps.text}
  //               isInvalid={!!errors?.text}
  //             />
  //             <Form.Control.Feedback type="invalid">
  //               {errors?.text}
  //             </Form.Control.Feedback>
  //           </Form.Group>
  //         </Col>
  //       </Row>
  //     </Container>
  //   </Form>
  // );
}