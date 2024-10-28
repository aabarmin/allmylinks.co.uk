import { Typography, TypographySystem } from "@mui/joy";
import { HeaderBlockProps } from "./HeaderBlock";

export function HeaderBlockComponent(props: HeaderBlockProps) {
  const level = props.level.toLowerCase() as keyof TypographySystem
  const align = props.alignment.toLowerCase() as string
  return (
    <Typography level={level} sx={{ display: 'flex', justifyContent: align }}>{props.text}</Typography>
  );
}