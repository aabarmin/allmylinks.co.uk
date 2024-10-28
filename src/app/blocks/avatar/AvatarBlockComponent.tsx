import { Box } from "@mui/joy";
import Image from "next/image";
import { AvatarBlockProps } from "./AvatarBlock";

export default function AvatarBlockComponent(props: AvatarBlockProps) {
  return (
    <Box sx={{
      display: 'flex',
      justifyContent: 'center',
      alignItems: 'center',
      height: '200px'
    }}>
      <Image
        alt="Default avatar"
        width={110}
        height={110}
        style={{
          border: '2px solid black',
          borderRadius: '50%'
        }}
        src={props.imageUrl} />
    </Box>
  )
}