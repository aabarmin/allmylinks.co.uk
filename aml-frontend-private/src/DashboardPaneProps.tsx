import React from 'react';
import { Form, Button, Container, Row, Col } from 'react-bootstrap';

interface PageProps {
    backgroundColor: string;
}

interface DashboardPanePropsProps {
    currentPage: {
        pageId: string;
        pageProps: PageProps;
    };
    onSave: () => void;
}

export default function DashboardPaneProps({ currentPage, onSave }: DashboardPanePropsProps) {
    const { pageId, pageProps } = currentPage;

    return (
        <Container>
            <Form
                method="post"
                action={`/private/dashboard/${pageId}`}
                onSubmit={(e) => {
                    e.preventDefault();
                    onSave();
                }}
            >
                <Row className="mb-3">
                    <Col>
                        <Button type="submit" variant="primary">
                            Save
                        </Button>
                    </Col>
                </Row>

                {/* Page Divider */}
                <Row className="mb-3">
                    <Col>
                        <hr />
                    </Col>
                </Row>

                <Row>
                    <Col>
                        <Form.Group as={Row} className="mb-3">
                            <Form.Label htmlFor="backgroundColor" column>
                                Background color
                            </Form.Label>
                            <Col>
                                <Form.Control
                                    type="color"
                                    id="backgroundColor"
                                    name="backgroundColor"
                                    defaultValue={pageProps.backgroundColor}
                                    className="form-control-color"
                                />
                            </Col>
                        </Form.Group>
                    </Col>
                </Row>
            </Form>
        </Container>
    );
}