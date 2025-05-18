package dev.abarmin.aml.dashboard.rest.response;

public record FileResponse(
  String storage,
  String filePath,
  String publicUrl
) {
}
