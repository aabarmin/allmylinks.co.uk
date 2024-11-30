import { DashboardResponse } from "@/app/(api)/api/dashboard/DashboardResponse";

export async function getDashboard(): Promise<DashboardResponse> {
  const response = await fetch('/api/dashboard');
  return response.json();
}