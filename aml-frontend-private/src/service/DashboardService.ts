import axios from "axios";
import { plainToInstance } from "class-transformer";
import { DashboardModel } from "../model/DashboardModel";

export function getDashboard(): Promise<DashboardModel> {
  return new Promise(resolve => {
    axios
      .get("/private/api/dashboard")
      .then(response => {
        const data = response.data as Record<string, unknown>
        const dashboard = plainToInstance(DashboardModel, data)
        resolve(dashboard)
      })
  });
}