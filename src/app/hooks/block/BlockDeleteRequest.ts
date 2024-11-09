import { Block } from "@/app/model/Block";
import { StateChangeRequest, StateChangeRequestType } from "../StateChangeRequest";

export class BlockDeleteRequest<T extends object> implements StateChangeRequest {
  type: StateChangeRequestType = StateChangeRequestType.BLOCK_DELETE;
  block: Block<T>;

  constructor(block: Block<T>) {
    this.block = block;
  }
}