package dev.abarmin.aml.file;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.nio.file.Files;
import java.nio.file.Path;

@Controller
@RequiredArgsConstructor
@ConditionalOnProperty(name = "aml.file-service.backend", havingValue = "DISK")
public class FileController {
  private final FileRepository fileRepository;
  private final DiskStorageConfiguration configuration;

  @ResponseBody
  @GetMapping("/file/{userId}/{imagePath}")
  public Resource showFile(
    @PathVariable("userId") String userId,
    @PathVariable("imagePath") String imagePath) {
    final String filePath = userId + "/" + imagePath;
    final FileEntity fileEntity = fileRepository.findByPathAndStorage(filePath, Storage.DISK)
      .orElseThrow(() -> new IllegalArgumentException("File not found in the database"));

    final Path locationOnDisk = Path.of(configuration.getBasePath()).resolve(fileEntity.path());
    if (!Files.exists(locationOnDisk)) {
      throw new IllegalArgumentException("File not found on the disk");
    }

    return new FileSystemResource(locationOnDisk);
  }
}
