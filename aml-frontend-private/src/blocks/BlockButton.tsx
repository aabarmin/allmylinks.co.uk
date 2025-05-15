import React from 'react';
import { Container, Row, Col, Button } from 'react-bootstrap';

interface BlockButtonProps {
    block: {
        blockProps: {
            link: string;
            text: string;
            size: {
                htmlClass: string;
            };
            color: {
                htmlClass: string;
            };
        };
    };
}

export default function BlockButton({ block }: BlockButtonProps) {
    const { link, text, size, color } = block.blockProps;

    return (
        <Container className="preview-pane-block">
            <Row>
                <Col className="d-grid">
                    <Button
                        href={link}
                        target="_blank"
                        className={`${size.htmlClass} ${color.htmlClass}`}
                        variant="primary"
                    >
                        {text}
                    </Button>
                </Col>
            </Row>
        </Container>
    );
}