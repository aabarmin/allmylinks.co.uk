package dev.abarmin.aml.config;

import dev.abarmin.aml.file.Storage;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

@Data
@Validated
@ConfigurationProperties(prefix = "aml")
public class AppConfiguration {
  @NotEmpty
  private String baseUrl;

  @Valid
  @NotNull
  private AppConfiguration.MailService mailService = new MailService();

  @Valid
  private FileService fileService = new FileService();

  @Data
  public static class MailService {
    @NotEmpty
    private String fromEmail;

    @NotEmpty
    private String fromName;
  }

  @Data
  public static class FileService {
    @NotNull
    private Storage backend;
  }
}
