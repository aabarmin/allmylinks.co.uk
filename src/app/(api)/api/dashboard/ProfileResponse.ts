import { Profile } from "@prisma/client";

export type ProfileResponse = {
  profileActive: boolean;
  profileSlug: string;
  profileLink: string;
  profileDisplayLink: string;
}

export function toProfileResponse(profile: Profile): ProfileResponse {
  const profileUrl = `${process.env.BASE_URL}/l/${profile.link}`;
  return {
    profileActive: profile.active,
    profileSlug: profile.link,
    profileLink: profileUrl,
    profileDisplayLink: profileUrl.substring(profileUrl.indexOf('://') + 3)
  };
}