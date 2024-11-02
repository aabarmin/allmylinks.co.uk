import Typography from "@mui/material/Typography";
import { HeaderBlockProps } from "./HeaderBlock";

export function HeaderBlockComponent(props: HeaderBlockProps) {
  // const level = props.level.toLowerCase() as HeaderToTypographyAdapter
  const level = 'h3';
  const align = props.alignment.toLowerCase() as string
  return (
    <Typography
      variant={level}
      sx={{ display: 'flex', justifyContent: align }}>
      {props.text}
    </Typography>
  );
}