import React from 'react';
import { Container, Row, Col } from 'react-bootstrap';
import PreviewPane from './PreviewPane';

interface PageProps {
    getPageStyle: () => React.CSSProperties;
}

interface Block {
    blockType: {
        previewComponent: string;
    };
}

interface DashboardPreviewProps {
    currentPage: {
        pageProps: PageProps;
        pageBlocks: Block[];
    };
}

export default function DashboardPreview({ currentPage }: DashboardPreviewProps) {
    const { pageProps, pageBlocks } = currentPage;

    return (
        <Container>
            <Row>
                <Col className="preview-pane-container">
                    <PreviewPane pageProps={pageProps} pageBlocks={pageBlocks} />
                </Col>
            </Row>
        </Container>
    );
}