import React from 'react';
import { Container, Row, Col } from 'react-bootstrap';

interface SocialLink {
    url: string;
    network: {
        htmlClass: string;
    };
}

interface BlockSocialNetworksProps {
    block: {
        blockProps: {
            links: SocialLink[];
        };
    };
}

export default function BlockSocialNetworks({ block }: BlockSocialNetworksProps) {
    const { links } = block.blockProps;

    return (
        <Container className="preview-pane-block">
            <Row>
                <Col className="text-center">
                    {links.map((link, index) => (
                        <a
                            key={index}
                            href={link.url}
                            style={{ paddingLeft: '5px' }}
                            className="icon-link"
                            target="_blank"
                            rel="noopener noreferrer"
                        >
                            <i
                                className={`bi ${link.network.htmlClass}`}
                                style={{ fontSize: '2em' }}
                            ></i>
                        </a>
                    ))}
                </Col>
            </Row>
        </Container>
    );
}