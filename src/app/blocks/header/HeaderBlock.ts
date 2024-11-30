import { Block, BlockType } from "@/app/model/Block";

export enum HeaderLevel {
  H1 = 'H1',
  H2 = 'H2',
  H3 = 'H3'
}

export enum HeaderAlignment {
  CENTER = 'CENTER',
  LEFT = 'LEFT',
  RIGHT = 'RIGHT'
}

export class HeaderBlockProps {
  level: HeaderLevel = HeaderLevel.H1;
  alignment: HeaderAlignment = HeaderAlignment.LEFT;
  text: string = 'Put your text here';
}

export type HeaderBlockOptionalProps = {
  level?: HeaderLevel;
  alignment?: HeaderAlignment;
  text?: string;
}

export class HeaderBlock implements Block<HeaderBlockProps> {
  id: number;
  type: BlockType;
  order: number;
  props: HeaderBlockProps;

  constructor(id: number, order: number, props: HeaderBlockProps) {
    this.id = id;
    this.order = order;
    this.type = BlockType.BLOCK_HEADER;
    this.props = props;
  }
}

export function fromOptionalProps(p: HeaderBlockOptionalProps): HeaderBlockProps {
  const props = new HeaderBlockProps();
  props.level = p.level || HeaderLevel.H1;
  props.alignment = p.alignment || HeaderAlignment.LEFT;
  props.text = p.text || 'Put your text here';
  return props;
}
