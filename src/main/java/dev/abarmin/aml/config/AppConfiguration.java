package dev.abarmin.aml.config;

import dev.abarmin.aml.file.Storage;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NonNull;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

import java.util.ArrayList;
import java.util.List;

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
  private ImageService imageService = new ImageService();

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
    @NotNull
    private CommunicationChannel channel;

    @Valid
    @NonNull
    private TelegramBackofficeConfiguration telegramConfiguration = new TelegramBackofficeConfiguration();

    @Valid
    @NotNull
    private BackofficeDefaults defaults = new BackofficeDefaults();

    enum CommunicationChannel {
      EMAIL,
      TELEGRAM
    }
  }

  @Data
  public static class TelegramBackofficeConfiguration {
    @NotNull
    @NotEmpty
    private String botToken;

    @NotNull
    @NotEmpty
    private String adminChatId;
  }

  @Data
  public static class BackofficeDefaults {
    @NotEmpty
    private String adminProfile;
  }

  @Data
  public static class ImageService {
    @NotNull
    @Size(min = 1, max = 100)
    private List<String> allowedTypes = new ArrayList<>();

    @Positive
    private long maxFileSizeBytes;
  }
}
