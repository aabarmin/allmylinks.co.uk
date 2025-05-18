import type { BlockProps } from "./BlockProps";

export interface SocialNetworksBlockProps extends BlockProps {
  links: SocialNetworkLink[];
}

export type SocialNetwork = "FACEBOOK" | "TWITTER" | "X" | "INSTAGRAM";

export interface SocialNetworkLink {
  url: string;
  network: SocialNetwork;
}
