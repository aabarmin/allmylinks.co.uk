import { Block, BlockType } from "@/app/model/Block";

export class SocialNetwork {
  order: number;
  icon: string;
  link: string;

  constructor(order: number) {
    this.order = order;
    this.icon = '';
    this.link = '';
  }
}

export class SocialNetworksBlockProps {
  networks: SocialNetwork[] = [];
}

export class SocialNetworksBlock implements Block<SocialNetworksBlockProps> {
  id: number;
  type: BlockType;
  order: number;
  props: SocialNetworksBlockProps;

  constructor(id: number, order: number) {
    this.id = id;
    this.order = order;
    this.type = BlockType.BLOCK_SOCIAL_NETWORKS;
    this.props = new SocialNetworksBlockProps();
  }
}

