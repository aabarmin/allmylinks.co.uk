import type { ReactNode } from 'react';
import { Col, Container, Row } from 'react-bootstrap';
import { Facebook, Instagram, Twitter, TwitterX } from 'react-bootstrap-icons';
import type { BlockResponse } from '../model/BlockModel';
import type { SocialNetwork, SocialNetworksBlockProps } from '../model/SocialNetworksBlockProps';

interface Props {
  block: BlockResponse
}

function networkToIcon(network: SocialNetwork): ReactNode {
  const style: React.CSSProperties = { fontSize: '2em' }
  switch (network) {
    case "FACEBOOK": return <Facebook style={style} />
    case "INSTAGRAM": return <Instagram style={style} />
    case "TWITTER": return <Twitter style={style} />
    case "X": return <TwitterX style={style} />
  }
}

export default function BlockSocialNetworks({ block }: Props) {
  const props: SocialNetworksBlockProps = block.blockProps as SocialNetworksBlockProps;

  return (
    <Container className="preview-pane-block">
      <Row>
        <Col className="text-center">
          {props.links.map((link, index) => (
            <a
              key={index}
              href={link.url}
              style={{ paddingLeft: '5px' }}
              className='icon-link'
              target='_blank'
            >
              {networkToIcon(link.network)}
            </a>
          ))}

          {/* {links.map((link, index) => (
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
                    ))} */}
        </Col>
      </Row>
    </Container>
  );
}