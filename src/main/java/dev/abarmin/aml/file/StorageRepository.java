package dev.abarmin.aml.file;

public interface StorageRepository {
  FileSaveResponse save(FileSaveRequest request);
}
