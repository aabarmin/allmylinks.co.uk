package dev.abarmin.aml.file;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@ConditionalOnProperty(name = "aml.file-service.backend", havingValue = "DISK")
public class DiskStorageRepository implements StorageRepository {


  @Override
  public FileSaveResponse save(FileSaveRequest request) {
    return null;
  }
}
