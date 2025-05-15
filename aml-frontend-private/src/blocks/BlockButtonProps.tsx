import React from 'react';
import { Form, Button, Container, Row, Col } from 'react-bootstrap';
import BlockToolbar from './BlockToolbar';

interface BlockButtonProps {
    currentBlock: {
        blockProps: {
            text: string;
            link: string;
            size: string;
            color: string;
        };
        blockType: {
            type: string;
        };
        pageId: string;
        blockId: string;
    };
    buttonSizes: { name: string; displayName: string }[];
    buttonColors: { name: string; displayName: string }[];
    errors?: {
        text?: string;
        link?: string;
        size?: string;
        color?: string;
    };
}

export default function BlockButtonProps({
    currentBlock,
    buttonSizes,
    buttonColors,
    errors,
}: BlockButtonProps) {
    const { blockProps, blockType, pageId, blockId } = currentBlock;

    return (
        <Form
            action={`/private/dashboard/${pageId}/blocks/${blockId}`}
            method="post"
        >
            <Form.Control type="hidden" name="type" value={blockType.type} />

            <Container>
                {/* Block Toolbar */}
                <BlockToolbar block={currentBlock} />

                <Row>
                    <Col>
                        {/* Text Field */}
                        <Form.Group className="mb-3">
                            <Form.Label htmlFor="buttonText">Text:</Form.Label>
                            <Form.Control
                                id="buttonText"
                                name="text"
                                type="text"
                                defaultValue={blockProps.text}
                                isInvalid={!!errors?.text}
                                required
                            />
                            <Form.Control.Feedback type="invalid">
                                {errors?.text}
                            </Form.Control.Feedback>
                        </Form.Group>

                        {/* Link Field */}
                        <Form.Group className="mb-3">
                            <Form.Label htmlFor="buttonLink">Link:</Form.Label>
                            <Form.Control
                                id="buttonLink"
                                name="link"
                                type="text"
                                defaultValue={blockProps.link}
                                isInvalid={!!errors?.link}
                                required
                            />
                            <Form.Control.Feedback type="invalid">
                                {errors?.link}
                            </Form.Control.Feedback>
                        </Form.Group>

                        {/* Size Dropdown */}
                        <Form.Group className="mb-3">
                            <Form.Label htmlFor="buttonSize">Size:</Form.Label>
                            <Form.Select
                                id="buttonSize"
                                name="size"
                                defaultValue={blockProps.size}
                                isInvalid={!!errors?.size}
                            >
                                {buttonSizes.map((size) => (
                                    <option key={size.name} value={size.name}>
                                        {size.displayName}
                                    </option>
                                ))}
                            </Form.Select>
                            <Form.Control.Feedback type="invalid">
                                {errors?.size}
                            </Form.Control.Feedback>
                        </Form.Group>

                        {/* Color Dropdown */}
                        <Form.Group className="mb-3">
                            <Form.Label htmlFor="buttonColor">Color:</Form.Label>
                            <Form.Select
                                id="buttonColor"
                                name="color"
                                defaultValue={blockProps.color}
                                isInvalid={!!errors?.color}
                            >
                                {buttonColors.map((color) => (
                                    <option key={color.name} value={color.name}>
                                        {color.displayName}
                                    </option>
                                ))}
                            </Form.Select>
                            <Form.Control.Feedback type="invalid">
                                {errors?.color}
                            </Form.Control.Feedback>
                        </Form.Group>
                    </Col>
                </Row>
            </Container>
        </Form>
    );
}