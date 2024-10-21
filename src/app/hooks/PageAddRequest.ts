import { StateChangeRequest, StateChangeRequestType } from "./StateChangeRequest";

export class PageAddRequestPayload {

}

export class PageAddRequest implements StateChangeRequest<PageAddRequestPayload> {
  type: StateChangeRequestType;
  payload: PageAddRequestPayload;

  constructor(payload: PageAddRequestPayload) {
    this.type = StateChangeRequestType.PAGE_ADD;
    this.payload = payload;
  }
}