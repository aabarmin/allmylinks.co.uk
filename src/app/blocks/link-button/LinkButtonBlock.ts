import { Block, BlockType } from "@/app/model/Block";

export class LinkButtonBlockProps {
  text: string = 'Put your text here';
  link: string = 'https://www.example.com';
};

export type LinkButtonBlockOptionalProps = {
  text?: string;
  link?: string;
};

export class LinkButtonBlock implements Block<LinkButtonBlockProps> {
  id: number;
  type: BlockType;
  order: number;
  props: LinkButtonBlockProps;

  constructor(id: number, order: number, props: LinkButtonBlockProps) {
    this.id = id;
    this.order = order;
    this.type = BlockType.BLOCK_LINK_BUTTON;
    this.props = props;
  }
}

export function fromOptionalProps(p: LinkButtonBlockOptionalProps): LinkButtonBlockProps {
  const props: LinkButtonBlockProps = {
    text: p.text || 'Put your text here',
    link: p.link || 'https://www.example.com'
  };
  return props;
}
