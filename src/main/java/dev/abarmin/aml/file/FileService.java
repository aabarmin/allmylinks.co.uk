package dev.abarmin.aml.file;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FileService {
  private final StorageRepository repository;

  public FileSaveResponse save(@NonNull FileSaveRequest request) {
    return repository.save(request);
  }
}
