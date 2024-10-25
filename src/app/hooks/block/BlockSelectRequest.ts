import { Block } from "../../model/Block";
import { StateChangeRequest, StateChangeRequestType } from "../StateChangeRequest";

export class BlockSelectRequest<T extends object> implements StateChangeRequest {
  type: StateChangeRequestType;
  block: Block<T>;

  constructor(block: Block<T>) {
    this.type = StateChangeRequestType.BLOCK_SELECT;
    this.block = block;
  }
}