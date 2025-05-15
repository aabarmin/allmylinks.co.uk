import React from 'react';
import { Container, Row, Col } from 'react-bootstrap';

interface BlockHeaderProps {
    block: {
        blockProps: {
            level: {
                htmlTag: string;
            };
            alignment: {
                htmlClass: string;
            };
            text: string;
        };
    };
}

export default function BlockHeader({ block }: BlockHeaderProps) {
    const { level, alignment, text } = block.blockProps;

    const renderHeader = () => {
        switch (level.htmlTag) {
            case 'h1':
                return <h1 className={alignment.htmlClass}>{text}</h1>;
            case 'h2':
                return <h2 className={alignment.htmlClass}>{text}</h2>;
            case 'h3':
                return <h3 className={alignment.htmlClass}>{text}</h3>;
            default:
                return <p>{text}</p>;
        }
    };

    return (
        <Container className="preview-pane-block">
            <Row>
                <Col>{renderHeader()}</Col>
            </Row>
        </Container>
    );
}