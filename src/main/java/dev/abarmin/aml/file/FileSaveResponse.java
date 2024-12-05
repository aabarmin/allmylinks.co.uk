package dev.abarmin.aml.file;

public record FileSaveResponse(
  FileId fileId,
  String originalFilename
) {
}
