package dev.abarmin.aml.dashboard.rest.response;

public sealed interface BlockPropsResponse permits
  AvatarBlockPropsResponse,
  HeaderBlockPropsResponse,
  LinkButtonBlockPropsResponse,
  SocialNetworksBlockPropsResponse {
}
