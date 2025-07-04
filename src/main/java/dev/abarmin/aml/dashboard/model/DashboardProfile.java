package dev.abarmin.aml.dashboard.model;

import dev.abarmin.aml.file.FileId;

public record DashboardProfile(
  String profileLink,
  String profileShortLink,
  FileId profileQr,
  ProfileShare share
) {
}
