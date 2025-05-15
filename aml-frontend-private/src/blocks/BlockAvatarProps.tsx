import React, { useState } from 'react';
import { Form, Button, Container, Row, Col } from 'react-bootstrap';
import BlockToolbar from './BlockToolbar';

interface BlockAvatarProps {
    currentBlock: {
        blockProps: {
            showShareButton: boolean;
            avatarId: string | null;
            backgroundId: string | null;
        };
        blockType: {
            type: string;
        };
        pageId: string;
        blockId: string;
    };
    allowedImageTypes: string;
    avatarError?: string;
    backgroundError?: string;
}

export default function BlockAvatarProps({
    currentBlock,
    allowedImageTypes,
    avatarError,
    backgroundError,
}: BlockAvatarProps) {
    const [hasBackground, setHasBackground] = useState(currentBlock.blockProps.backgroundId !== null);

    const handleBackgroundToggle = () => {
        setHasBackground(!hasBackground);
    };

    return (
        <Form
            action={`/private/dashboard/${currentBlock.pageId}/blocks/${currentBlock.blockId}`}
            method="post"
            encType="multipart/form-data"
        >
            <Form.Control type="hidden" name="type" value={currentBlock.blockType.type} />

            <Container>
                {/* Block Toolbar */}
                <BlockToolbar block={currentBlock} />

                <Row>
                    <Col>
                        <Form.Group className="mb-3">
                            <Form.Check
                                type="switch"
                                id="showShareButton"
                                label="Show share button"
                                name="showShareButton"
                                defaultChecked={currentBlock.blockProps.showShareButton}
                                className="form-check-reverse"
                            />
                        </Form.Group>
                    </Col>
                </Row>

                <Row>
                    <Col>
                        <Form.Group className="mb-3 text-center">
                            {currentBlock.blockProps.avatarId && (
                                <img
                                    src={currentBlock.blockProps.avatarId}
                                    className="rounded-circle"
                                    height="200"
                                    width="200"
                                    alt="Avatar"
                                />
                            )}
                        </Form.Group>

                        <Form.Group className="mb-3">
                            <Form.Label htmlFor="newAvatar">Upload new avatar</Form.Label>
                            <Form.Control
                                type="file"
                                id="newAvatar"
                                name="newAvatar"
                                accept={allowedImageTypes}
                                isInvalid={!!avatarError}
                            />
                            <Form.Control.Feedback type="invalid">{avatarError}</Form.Control.Feedback>
                        </Form.Group>
                    </Col>
                </Row>

                <Row>
                    <Col>
                        <Form.Group className="mb-3">
                            <Form.Check
                                type="switch"
                                id="hasBackgroundCheckbox"
                                label="Has background"
                                name="hasBackground"
                                checked={hasBackground}
                                onChange={handleBackgroundToggle}
                                className="form-check-reverse"
                            />
                        </Form.Group>

                        {hasBackground && (
                            <>
                                <Form.Group className="mb-3" id="backgroundPreview">
                                    {currentBlock.blockProps.backgroundId && (
                                        <img
                                            src={currentBlock.blockProps.backgroundId}
                                            className="img-fluid"
                                            alt="Background"
                                        />
                                    )}
                                </Form.Group>

                                <Form.Group className="mb-3" id="backgroundUpload">
                                    <Form.Label htmlFor="newBackground">Upload new background</Form.Label>
                                    <Form.Control
                                        type="file"
                                        id="newBackground"
                                        name="newBackground"
                                        accept={allowedImageTypes}
                                        isInvalid={!!backgroundError}
                                    />
                                    <Form.Control.Feedback type="invalid">{backgroundError}</Form.Control.Feedback>
                                </Form.Group>
                            </>
                        )}
                    </Col>
                </Row>
            </Container>
        </Form>
    );
}