package dev.abarmin.aml.version;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.io.InputStream;
import java.util.Properties;

@Slf4j
@ControllerAdvice
public class VersionControllerAdvice {
  private String version;

  @PostConstruct
  public void init() throws Exception {
    final Resource versionStorage = new ClassPathResource("/version.properties");
    if (!versionStorage.exists()) {
      log.warn("version.properties file not found");
      version = "unknown";
      return;
    }
    final Properties properties = new Properties();
    try (final InputStream inputStream = versionStorage.getInputStream()) {
      properties.load(inputStream);
    }
    if (!properties.containsKey("version")) {
      log.warn("version.properties file does not contain version key");
      version = "unknown";
      return;
    }
    version = properties.getProperty("version");
  }

  @ModelAttribute("version")
  public String getVersion() {
    return version;
  }
}
