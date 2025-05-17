import type { BlockProps } from "./BlockProps";

export type HeaderLevel = "H1" | "H2" | "H3";
export type HeaderTextAlignment = "LEFT" | "CENTER" | "RIGHT";

export interface HeaderBlockProps extends BlockProps {
  text: string;
  level: HeaderLevel;
  alignment: HeaderTextAlignment;
}
