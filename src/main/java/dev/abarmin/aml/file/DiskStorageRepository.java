package dev.abarmin.aml.file;

import groovy.util.logging.Slf4j;
import jakarta.annotation.PostConstruct;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.UUID;

@Slf4j
@Component
@RequiredArgsConstructor
@ConditionalOnProperty(name = "aml.file-service.backend", havingValue = "DISK")
public class DiskStorageRepository implements StorageRepository {
  private final DiskStorageConfiguration configuration;

  @PostConstruct
  public void init() throws Exception {
    final Path basePath = Path.of(configuration.getBasePath());
    Files.createDirectories(basePath);
  }

  @Override
  @SneakyThrows
  public FileSaveResponse save(@NonNull FileSaveRequest request) {
    // check if user's directory exists
    final Path ownerDirectory = Path.of(configuration.getBasePath()).resolve("user-" + request.owner().id());
    Files.createDirectories(ownerDirectory);
    // generate target file name
    final String extension = StringUtils.substringAfterLast(request.originalFilename(), ".");
    Path targetPath = nextFilePath(ownerDirectory, extension);
    while (Files.exists(targetPath)) {
      targetPath = nextFilePath(ownerDirectory, extension);
    }
    // finally save the record
    try (final OutputStream outputStream = Files.newOutputStream(targetPath, StandardOpenOption.CREATE_NEW)) {
      IOUtils.copy(request.fileContent(), outputStream);
    }
    final int nameParts = targetPath.getNameCount();
    final String filePath = targetPath.subpath(nameParts - 2, nameParts).toString();
    // done, build the response back
    return new FileSaveResponse(
      FileId.disk(filePath),
      request.originalFilename()
    );
  }

  private Path nextFilePath(Path parentDirectory, String extension) {
    final StringBuilder filename = new StringBuilder();
    filename.append(UUID.randomUUID().toString());
    if (StringUtils.isNotEmpty(extension)) {
      filename.append(".").append(extension);
    }
    return parentDirectory.resolve(filename.toString());
  }
}
