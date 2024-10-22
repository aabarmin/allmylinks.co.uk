import { StateChangeRequest, StateChangeRequestType } from "../StateChangeRequest";

export class PageAddRequest implements StateChangeRequest {
  type: StateChangeRequestType;
  name: string;

  constructor(name: string) {
    this.type = StateChangeRequestType.PAGE_ADD;
    this.name = name;
  }
}