import { Page } from "@/app/model/Page";
import { StateChangeRequest, StateChangeRequestType } from "../StateChangeRequest";

export class PageDeleteRequest implements StateChangeRequest {
  type: StateChangeRequestType;
  page: Page;

  constructor(page: Page) {
    this.type = StateChangeRequestType.PAGE_DELETE;
    this.page = page;
  }
}