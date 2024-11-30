import Typography from "@mui/material/Typography";
import { HeaderBlockProps, HeaderLevel } from "./HeaderBlock";

type TypographyHeaderLevel = 'h3' | 'h4' | 'h5' | 'h6';

function toTypographyLevel(l: HeaderLevel): TypographyHeaderLevel {
  switch (l) {
    case HeaderLevel.H1: return 'h3';
    case HeaderLevel.H2: return 'h4';
    case HeaderLevel.H3: return 'h5';
    default: return 'h6';
  }
}

export function HeaderBlockComponent(props: HeaderBlockProps) {
  const level = toTypographyLevel(props.level);
  const align = props.alignment.toLowerCase() as string
  return (
    <Typography
      variant={level}
      sx={{ display: 'flex', justifyContent: align }}>
      {props.text}
    </Typography>
  );
}