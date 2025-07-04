package dev.abarmin.aml.config;

import dev.abarmin.aml.file.FileIdFormatter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class ThymeleafFormatterConfiguration implements WebMvcConfigurer {
  private final FileIdFormatter fileIdFormatter;

  @Override
  public void addFormatters(FormatterRegistry registry) {
    registry.addFormatter(fileIdFormatter);
  }
}
