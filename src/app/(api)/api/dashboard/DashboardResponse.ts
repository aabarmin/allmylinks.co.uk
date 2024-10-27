import { PageResponse } from "./PageResponse";
import { ProfileResponse } from "./ProfileResponse";

export class DashboardResponse {
  profile: ProfileResponse;
  pages: PageResponse;

  constructor(profile: ProfileResponse, pages: PageResponse) {
    this.profile = profile;
    this.pages = pages;
  }
}