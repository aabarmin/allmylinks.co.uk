package dev.abarmin.aml.file;

public record FileSaveResponse(
  Storage storage,
  String originalFilename,
  String filePath
) {
}
