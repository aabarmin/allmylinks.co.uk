import { Page } from "@/app/model/Page";
import { StateChangeRequest, StateChangeRequestType } from "../StateChangeRequest";

export class PageUpdateRequest implements StateChangeRequest {
  type: StateChangeRequestType;
  page: Page;
  callback: (page: Page) => Page;

  constructor(page: Page, callback: (page: Page) => Page) {
    this.type = StateChangeRequestType.PAGE_UPDATE;
    this.page = page;
    this.callback = callback;
  }
}