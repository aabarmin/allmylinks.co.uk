package dev.abarmin.aml.dashboard.rest.converter;

import dev.abarmin.aml.dashboard.rest.response.FileResponse;
import dev.abarmin.aml.file.FileId;
import dev.abarmin.aml.file.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FileResponseConverter {

  private final FileService fileService;

  public FileResponse convert(FileId fileId) {
    return new FileResponse(
      fileId.storage().name(),
      fileId.filePath(),
      fileService.getPublicUrl(fileId)
    );
  }

}
