import { Block } from "../../model/Block";
import { Page } from "../../model/Page";
import { StateChangeRequest, StateChangeRequestType } from "../StateChangeRequest";

export class BlockAddRequest<T extends object> implements StateChangeRequest {
  type: StateChangeRequestType;
  page: Page;
  block: Block<T>;

  constructor(page: Page, block: Block<T>) {
    this.type = StateChangeRequestType.BLOCK_ADD;
    this.page = page;
    this.block = block;
  }
}