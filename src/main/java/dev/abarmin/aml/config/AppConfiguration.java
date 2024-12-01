package dev.abarmin.aml.config;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

@Data
@Validated
@ConfigurationProperties(prefix = "aml", ignoreUnknownFields = false)
public class AppConfiguration {
  @NotEmpty
  private String baseUrl;
}
