package dev.abarmin.aml.file;

import dev.abarmin.aml.registration.domain.User;
import lombok.NonNull;
import lombok.SneakyThrows;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.function.Function;

public record FileSaveRequest(
  User owner,
  String originalFilename,
  InputStream fileContent) {

  @SneakyThrows
  public static <T> T of(@NonNull User owner, @NonNull MultipartFile file, @NonNull Function<FileSaveRequest, T> processor) {
    try (final InputStream inputStream = file.getInputStream()) {
      final FileSaveRequest request = new FileSaveRequest(owner, file.getOriginalFilename(), inputStream);
      return processor.apply(request);
    }
  }

  @SneakyThrows
  public static <T> T of(@NonNull User owner, @NonNull Path path, @NonNull Function<FileSaveRequest, T> processor) {
    try (final InputStream inputStream = Files.newInputStream(path)) {
      final FileSaveRequest request = new FileSaveRequest(owner, path.getFileName().toString(), inputStream);
      return processor.apply(request);
    }
  }
}
