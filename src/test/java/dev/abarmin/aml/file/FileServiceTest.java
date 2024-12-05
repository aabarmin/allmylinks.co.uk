package dev.abarmin.aml.file;

import dev.abarmin.aml.registration.domain.User;
import dev.abarmin.aml.utils.TestUserFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {
  FileService.class,
  DiskStorageRepository.class
})
@EnableConfigurationProperties(DiskStorageConfiguration.class)
@TestPropertySource(properties = {
  "aml.file-service.backend=DISK"
})
class FileServiceTest {
  @Autowired
  FileService fileService;

  @DynamicPropertySource
  static void configure(DynamicPropertyRegistry registry) throws Exception {
    final String temporaryDirectory = Files.createTempDirectory("test").toFile().getPath();
    registry.add("aml.file-service.disk-storage.base-path", () -> temporaryDirectory);
  }

  @Test
  void save_shouldSaveFile() throws Exception {
    final Path tempFilePath = Files.createTempFile("temp", ".txt");
    Files.writeString(tempFilePath, "Hello, World!");

    final User user = TestUserFactory.build();

    try (final InputStream inputStream = Files.newInputStream(tempFilePath)) {
      final FileSaveRequest request = new FileSaveRequest(user, "test.txt", inputStream);

      final FileSaveResponse response = fileService.save(request);

      assertThat(response).isNotNull();
    }
  }
}
