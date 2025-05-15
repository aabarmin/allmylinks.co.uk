import React from 'react';
import { Container, Row, Col } from 'react-bootstrap';

export default function BlockMadeWithAml() {
    return (
        <Container className="preview-pane-block">
            <Row>
                <Col className="text-center">
                    <p style={{ color: '#aaaaaa' }} className="fs-6 fw-lighter">
                        Made with{' '}
                        <a
                            href="https://allmylinks.co.uk"
                            className="fw-bold"
                            style={{ color: '#aaaaaa' }}
                            target="_blank"
                            rel="noopener noreferrer"
                        >
                            AllMyLinks
                        </a>
                    </p>
                </Col>
            </Row>
        </Container>
    );
}