package dev.abarmin.aml.dashboard;

import dev.abarmin.aml.config.AppConfiguration;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
@RequiredArgsConstructor
public class AvatarValidator {
  private final AppConfiguration configuration;

  public ValidationResult validate(@NonNull final MultipartFile multipartFile) {
    if (multipartFile.isEmpty()) {
      return ValidationResult.ok();
    }
    final String contentType = multipartFile.getContentType();
    if (!isAllowedType(contentType)) {
      return ValidationResult.of("Invalid file type");
    }
    final long fileSize = multipartFile.getSize();
    if (!isAllowedSize(fileSize)) {
      return ValidationResult.of("File is too big");
    }

    return ValidationResult.ok();
  }

  private boolean isAllowedSize(long fileSize) {
    return fileSize <= configuration.getImageService().getMaxFileSizeBytes();
  }

  private boolean isAllowedType(String contentType) {
    if (StringUtils.isEmpty(contentType)) {
      return false;
    }
    return configuration.getImageService()
      .getAllowedTypes()
      .contains(contentType);
  }

  public record ValidationResult(
    boolean isOk,
    String message
  ) {
    public static ValidationResult ok() {
      return new ValidationResult(true, null);
    }

    public static ValidationResult of(String message) {
      return new ValidationResult(false, message);
    }
  }
}
