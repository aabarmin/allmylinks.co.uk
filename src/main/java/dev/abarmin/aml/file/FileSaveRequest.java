package dev.abarmin.aml.file;

import dev.abarmin.aml.registration.domain.User;

import java.io.InputStream;

public record FileSaveRequest(
  User owner,
  String originalFilename,
  InputStream fileContent) {
}
