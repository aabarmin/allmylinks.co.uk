import { Block } from "../model/Block";
import { StateChangeRequest, StateChangeRequestType } from "./StateChangeRequest";

export class BlockSelectRequest implements StateChangeRequest {
  type: StateChangeRequestType;
  block: Block<any>;

  constructor(block: Block<any>) {
    this.type = StateChangeRequestType.BLOCK_SELECT;
    this.block = block;
  }
}