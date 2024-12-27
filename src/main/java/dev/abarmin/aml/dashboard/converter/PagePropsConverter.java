package dev.abarmin.aml.dashboard.converter;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.abarmin.aml.dashboard.domain.PageProps;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;
import org.springframework.data.convert.WritingConverter;

public class PagePropsConverter {
  @WritingConverter
  @RequiredArgsConstructor
  public static class Writer implements Converter<PageProps, String> {
    private final ObjectMapper objectMapper;

    @Override
    @SneakyThrows
    public String convert(PageProps source) {
      return objectMapper.writeValueAsString(source);
    }
  }

  @ReadingConverter
  @RequiredArgsConstructor
  public static class Reader implements Converter<String, PageProps> {
    private final ObjectMapper objectMapper;

    @Override
    @SneakyThrows
    public PageProps convert(String source) {
      return objectMapper.readValue(source, PageProps.class);
    }
  }
}
