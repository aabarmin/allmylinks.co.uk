import type { BlockProps } from "./BlockProps";

export type LinkButtonSize = "NORMAL" | "SMALL" | "LARGE";
export type LinkButtonColor = "BLUE" | "GRAY" | "GREEN" | "RED" | "YELLOW" | "LIGHT_BLUE" | "LIGHT_GREY" | "DARK" | "REGULAR";

export interface LinkButtonBlockProps extends BlockProps {
  text: string;
  link: string;
  size: LinkButtonSize;
  color: LinkButtonColor;
}