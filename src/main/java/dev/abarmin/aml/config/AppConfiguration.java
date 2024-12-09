package dev.abarmin.aml.config;

import dev.abarmin.aml.file.Storage;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
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
  @NotNull
  private FileService fileService = new FileService();

  @Valid
  @NotNull
  private Backoffice backoffice = new Backoffice();

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

  @Data
  public static class Backoffice {
    @Email
    @NotEmpty
    private String adminEmail;
  }
}
