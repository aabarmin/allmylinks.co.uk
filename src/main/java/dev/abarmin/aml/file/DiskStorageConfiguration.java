package dev.abarmin.aml.file;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

@Data
@Validated
@ConfigurationProperties(prefix = "aml.file-service.disk-storage", ignoreUnknownFields = false)
@ConditionalOnProperty(name = "aml.file-service.backend", havingValue = "DISK")
public class DiskStorageConfiguration {
  @NotEmpty
  private String basePath;
}
