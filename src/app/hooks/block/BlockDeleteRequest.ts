import { Block } from "@/app/model/Block";
import { Page } from "@/app/model/Page";
import { StateChangeRequest, StateChangeRequestType } from "../StateChangeRequest";

export class BlockDeleteRequest<T extends object> implements StateChangeRequest {
  type: StateChangeRequestType = StateChangeRequestType.BLOCK_DELETE;
  page: Page;
  block: Block<T>

  constructor(page: Page, block: Block<T>) {
    this.page = page;
    this.block = block;
  }
}