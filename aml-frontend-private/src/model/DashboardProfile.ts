import type { FileId } from "./FileId";
import type { ProfileShare } from "./ProfileShare";

export class DashboardProfile {
  profileLink: string;
  profileShortLink: string;
  profileQr: FileId;
  share: ProfileShare;

  constructor(
    profileLink: string,
    profileShortLink: string,
    profileQr: FileId,
    share: ProfileShare
  ) {
    this.profileLink = profileLink;
    this.profileShortLink = profileShortLink;
    this.profileQr = profileQr;
    this.share = share;
  }
}