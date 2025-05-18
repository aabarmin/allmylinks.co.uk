export class ProfileShare {
  onTwitter: string;
  onFacebook: string;
  onLinkedin: string;

  constructor(onTwitter: string, onFacebook: string, onLinkedin: string) {
    this.onTwitter = onTwitter;
    this.onFacebook = onFacebook;
    this.onLinkedin = onLinkedin;
  }
}