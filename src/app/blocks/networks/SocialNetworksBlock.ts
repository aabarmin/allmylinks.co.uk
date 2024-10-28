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

export type SocialNetworksOptionalProps = {
  networks?: {
    icon?: string;
    link?: string;
    order?: number;
  }[]
}

export class SocialNetworksBlock implements Block<SocialNetworksBlockProps> {
  id: number;
  type: BlockType;
  order: number;
  props: SocialNetworksBlockProps;

  constructor(id: number, order: number, props: SocialNetworksBlockProps) {
    this.id = id;
    this.order = order;
    this.type = BlockType.BLOCK_SOCIAL_NETWORKS;
    this.props = props;
  }
}

export function fromOptionalProps(p: SocialNetworksOptionalProps): SocialNetworksBlockProps {
  const props = new SocialNetworksBlockProps();
  props.networks = p.networks?.map(n => {
    const item = new SocialNetwork(n?.order || 0);
    item.icon = n?.icon || '';
    item.link = n?.link || '';
    return item;
  }) || [];
  return props;
}
