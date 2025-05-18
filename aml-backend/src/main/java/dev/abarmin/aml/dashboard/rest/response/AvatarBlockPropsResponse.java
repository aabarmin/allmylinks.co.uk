package dev.abarmin.aml.dashboard.rest.response;

public record AvatarBlockPropsResponse(
  FileResponse avatar,
  FileResponse background,
  boolean showShareButton
) implements BlockPropsResponse {
}
