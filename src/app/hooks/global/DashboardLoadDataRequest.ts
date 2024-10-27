import { GetDashboardResponse } from "@/app/(api)/api/dashboard/route";
import { StateChangeRequest, StateChangeRequestType } from "../StateChangeRequest";

export class DashboardLoadDataRequest implements StateChangeRequest {
  type: StateChangeRequestType;
  data: GetDashboardResponse;

  constructor(data: GetDashboardResponse) {
    this.type = StateChangeRequestType.DASHBOARD_LOAD_DATA;
    this.data = data;
  }
}