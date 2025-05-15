import type { BlockTypeModel } from "./BlockTypeModel";
import type { DashboardProfile } from "./DashboardProfile";

export class DashboardModel {
    profile: DashboardProfile;
    availableBlocks: BlockTypeModel[];

    constructor(profile: DashboardProfile, availableBlocks: BlockTypeModel[]) {
        this.profile = profile;
        this.availableBlocks = availableBlocks;
    }

}