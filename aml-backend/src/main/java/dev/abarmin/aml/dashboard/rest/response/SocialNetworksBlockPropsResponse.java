package dev.abarmin.aml.dashboard.rest.response;

import java.util.Collection;

public record SocialNetworksBlockPropsResponse(
  Collection<SocialNetworkLinkResponse> links
) implements BlockPropsResponse {
}
