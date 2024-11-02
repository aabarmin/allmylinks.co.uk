import Box from "@mui/material/Box";
import { SocialIcon } from "react-social-icons";
import { SocialNetworksBlockProps } from "./SocialNetworksBlock";

export function SocialNetworksBlockComponent(props: SocialNetworksBlockProps) {
  return (
    <Box sx={{
      display: 'flex',
      flexDirection: 'row',
      justifyContent: 'center',
      gap: 2
    }}>
      {props.networks.map(n => (
        <SocialIcon key={n.order} network={n.icon} url={n.link} />
      ))}
    </Box>
  )
}