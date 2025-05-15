import type { DashboardProfile } from "./DashboardProfile";

export class DashboardModel {
    profile: DashboardProfile;

    constructor(profile: DashboardProfile) {
        this.profile = profile;
    }

}