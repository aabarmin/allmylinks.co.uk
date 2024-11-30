import { DashboardResponse } from "@/app/(api)/api/dashboard/DashboardResponse";
import { StateChangeRequest, StateChangeRequestType } from "../StateChangeRequest";

export class DashboardLoadDataRequest implements StateChangeRequest {
  type: StateChangeRequestType;
  data: DashboardResponse;

  constructor(data: DashboardResponse) {
    this.type = StateChangeRequestType.DASHBOARD_LOAD_DATA;
    this.data = data;
  }
}