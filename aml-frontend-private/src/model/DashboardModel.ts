import type { BlockTypeModel } from "./BlockTypeModel";
import type { DashboardProfile } from "./DashboardProfile";
import type { PageModel } from "./PageModel";

export class DashboardModel {
    profile: DashboardProfile;
    availableBlocks: BlockTypeModel[];
    currentPage: PageModel;

    constructor(profile: DashboardProfile, availableBlocks: BlockTypeModel[], currentPage: PageModel) {
        this.profile = profile;
        this.availableBlocks = availableBlocks;
        this.currentPage = currentPage;
    }

}