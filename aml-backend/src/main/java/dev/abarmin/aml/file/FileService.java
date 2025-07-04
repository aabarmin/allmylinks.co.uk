package dev.abarmin.aml.file;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FileService {
  private final StorageRepository storage;
  private final FileRepository repository;

  public FileSaveResponse save(@NonNull FileSaveRequest request) {
    final FileSaveResponse savedFile = storage.save(request);
    final FileEntity fileEntity = new FileEntity(
      request.owner().id(),
      savedFile.fileId()
    );
    repository.save(fileEntity);
    return savedFile;
  }

  public String getPublicUrl(@NonNull FileId fileId) {
    return switch (fileId.storage()) {
      case RESOURCE -> fileId.filePath();
      case DISK -> "/file/" + fileId.filePath();
    };
  }
}
