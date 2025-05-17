import { Button, Col, Row } from 'react-bootstrap';
import { Share } from 'react-bootstrap-icons';
import type { AvatarBlockProps } from '../model/AvatarBlockProps';
import type { BlockResponse } from '../model/BlockModel';

interface Props {
  block: BlockResponse
}

function ShareButton() {
  return (
    <div className='avatar-share-button'>
      <Button variant='outline-primary'>
        <Share />
      </Button>
    </div>
  );
}

function Background({ block, props }: { block: BlockResponse, props: AvatarBlockProps }) {
  const className = 'avatar-background' + (block.canMoveUp == false ? 'avatar-background-rounded-up' : '');
  const customStyle: React.CSSProperties = {};
  if (props.background) {
    customStyle.backgroundRepeat = 'no-repeat';
    customStyle.backgroundPosition = 'center';
    customStyle.backgroundImage = `url(${props.background.publicUrl})`;
  }
  return (
    <div className={className} style={customStyle}></div>
  );
}

function Avatar({ props }: { props: AvatarBlockProps }) {
  const className = 'avatar-photo-container ' + (props.background ? '' : 'avatar-photo-container-no-background');
  const customStyle: React.CSSProperties = {};
  if (props.background) {
    customStyle.borderColor = '#ffffff';
    customStyle.borderWidth = '5px';
    customStyle.borderStyle = 'solid';
  }
  return (
    <div className={className}>
      <img
        src={props.avatar.publicUrl}
        className='avatar-photo rounded-circle'
        width={200}
        height={200}
        style={customStyle}
      />
    </div>
  );
}

export default function BlockAvatar({ block }: Props) {
  const props: AvatarBlockProps = block.blockProps as AvatarBlockProps;
  const colClassName = "avatar-container " + (props.background ? '' : 'avatar-container-no-background');

  return (
    <div className="preview-pane-block-no-padding">
      <Row style={{ marginLeft: 0, marginRight: 0 }}>
        <Col className={colClassName}>
          {props.showShareButton && <ShareButton />}
          {props.background && <Background props={props} block={block} />}
          <Avatar props={props} />
        </Col>
      </Row>
    </div>
  );
}