import Box from "@mui/material/Box";
import { SocialIcon } from "react-social-icons";
import { SocialNetwork, SocialNetworksBlockProps } from "./SocialNetworksBlock";

function getUrl(n: SocialNetwork): string {
  if (n.icon == 'twitter') {
    if (n.link.startsWith('http')) {
      return n.link;
    }
    return `https://x.com/${n.link}`;
  }
  if (n.icon == 'facebook') {
    if (n.link.startsWith('http')) {
      return n.link;
    }
    return `https://facebook.com/${n.link}`;
  }
  if (n.icon == 'instagram') {
    if (n.link.startsWith('http')) {
      return n.link;
    }
    return `https://instagram.com/${n.link}`;
  }

  throw new Error("Not implemented");
}

export function SocialNetworksBlockComponent(props: SocialNetworksBlockProps) {
  return (
    <Box sx={{
      display: 'flex',
      flexDirection: 'row',
      justifyContent: 'center',
      gap: 2
    }}>
      {props.networks.map(n => (
        <SocialIcon key={n.order} network={n.icon} url={getUrl(n)} target="_blank" />
      ))}
    </Box>
  )
}