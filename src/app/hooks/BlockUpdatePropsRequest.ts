import { Block } from "../model/Block";
import { StateChangeRequest, StateChangeRequestType } from "./StateChangeRequest";

export class BlockUpdatePropsRequest<T> implements StateChangeRequest {
  type: StateChangeRequestType;
  block: Block<T>;
  callback: (arg: T) => T;

  constructor(block: Block<T>, callback: (arg: T) => T) {
    this.type = StateChangeRequestType.BLOCK_UPDATE_PROPS
    this.block = block;
    this.callback = callback;
  }
}