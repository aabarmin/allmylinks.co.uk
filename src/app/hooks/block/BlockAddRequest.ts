import { Block } from "../model/Block";
import { Page } from "../model/Page";
import { StateChangeRequest, StateChangeRequestType } from "./StateChangeRequest";

export class BlockAddRequest implements StateChangeRequest {
  type: StateChangeRequestType;
  page: Page;
  block: Block<any>;

  constructor(page: Page, block: Block<any>) {
    this.type = StateChangeRequestType.BLOCK_ADD;
    this.page = page;
    this.block = block;
  }
}