import { JsonValue } from "@prisma/client/runtime/library";
import { BlockType } from "./Block";


export type BlockLikeRecord = {
  id: number;
  order: number;
  type: BlockType | string;
  props: object | JsonValue;
};
