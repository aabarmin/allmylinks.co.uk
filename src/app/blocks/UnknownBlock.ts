import { Block, BlockType } from "../model/Block";

export type UnknownBlockProps = {
  realType: string;
};

export class UnknownBlock implements Block<UnknownBlockProps> {
  id: number;
  type: BlockType = BlockType.UNKNOWN;
  order: number;
  props: UnknownBlockProps;

  constructor(id: number, order: number, props: UnknownBlockProps) {
    this.id = id;
    this.order = order;
    this.props = props;
  }
}