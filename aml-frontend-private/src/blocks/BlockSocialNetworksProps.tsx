import React from 'react';
import { Form, Container, Row, Col, Button, Table } from 'react-bootstrap';
import BlockToolbar from './BlockToolbar';

interface SocialLink {
    url: string;
    network: string;
}

interface BlockSocialNetworksProps {
    currentBlock: {
        blockProps: {
            links: SocialLink[];
        };
        blockType: {
            type: string;
        };
        pageId: string;
        blockId: string;
    };
    socialNetworks: { name: string }[];
    errors?: {
        links?: { network?: string; url?: string }[];
    };
    onAddNetwork: () => void;
    onRemoveNetwork: (index: number) => void;
}

export default function BlockSocialNetworksProps({
    currentBlock,
    socialNetworks,
    errors,
    onAddNetwork,
    onRemoveNetwork,
}: BlockSocialNetworksProps) {
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

                <Row className="mb-3">
                    <Col className="d-grid">
                        <Button
                            variant="primary"
                            onClick={onAddNetwork}
                            type="button"
                        >
                            Add social network
                        </Button>
                    </Col>
                </Row>

                <Row>
                    <Col>
                        <Table>
                            <tbody>
                                {blockProps.links.map((link, index) => (
                                    <tr key={index}>
                                        <td>
                                            <Form.Group>
                                                <Form.Select
                                                    name={`blockProps.links[${index}].network`}
                                                    defaultValue={link.network}
                                                    isInvalid={
                                                        !!errors?.links?.[index]?.network
                                                    }
                                                >
                                                    {socialNetworks.map((sn) => (
                                                        <option
                                                            key={sn.name}
                                                            value={sn.name}
                                                        >
                                                            {sn.name}
                                                        </option>
                                                    ))}
                                                </Form.Select>
                                                <Form.Control.Feedback type="invalid">
                                                    {errors?.links?.[index]?.network}
                                                </Form.Control.Feedback>
                                            </Form.Group>
                                        </td>
                                        <td>
                                            <Form.Group>
                                                <Form.Control
                                                    type="text"
                                                    name={`blockProps.links[${index}].url`}
                                                    defaultValue={link.url}
                                                    isInvalid={
                                                        !!errors?.links?.[index]?.url
                                                    }
                                                    required
                                                />
                                                <Form.Control.Feedback type="invalid">
                                                    {errors?.links?.[index]?.url}
                                                </Form.Control.Feedback>
                                            </Form.Group>
                                        </td>
                                        <td>
                                            <Button
                                                variant="danger"
                                                onClick={() => onRemoveNetwork(index)}
                                                type="button"
                                            >
                                                <i className="bi bi-trash"></i>
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