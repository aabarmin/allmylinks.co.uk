package dev.abarmin.aml.file;

import groovy.util.logging.Slf4j;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import java.nio.file.Files;
import java.nio.file.Path;

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
  public FileSaveResponse save(FileSaveRequest request) {
    return null;
  }
}
