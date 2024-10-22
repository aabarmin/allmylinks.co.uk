import { StateChangeRequest, StateChangeRequestType } from "../StateChangeRequest";

export class LeftPanelChangeRequest implements StateChangeRequest {
  type: StateChangeRequestType;
  componentAlias: string;

  constructor(componentAlias: string) {
    this.type = StateChangeRequestType.LEFT_PANEL_CHANGE;
    this.componentAlias = componentAlias;
  }
}