package dev.abarmin.aml.dashboard.rest.response;

import dev.abarmin.aml.dashboard.block.social.SocialNetworkIcon;

public record SocialNetworkLinkResponse(
  String url,
  SocialNetworkIcon network
) {
}
