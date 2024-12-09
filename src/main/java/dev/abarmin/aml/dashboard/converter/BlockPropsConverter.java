package dev.abarmin.aml.dashboard.converter;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.abarmin.aml.dashboard.domain.BlockProps;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;
import org.springframework.data.convert.WritingConverter;

public class BlockPropsConverter {
  @WritingConverter
  @RequiredArgsConstructor
  public static class Writer implements Converter<BlockProps, String> {
    private final ObjectMapper objectMapper;

    @Override
    @SneakyThrows
    public String convert(BlockProps source) {
      return objectMapper.writeValueAsString(source);
    }
  }

  @ReadingConverter
  @RequiredArgsConstructor
  public static class Reader implements Converter<String, BlockProps> {
    private final ObjectMapper objectMapper;

    @Override
    @SneakyThrows
    public BlockProps convert(String source) {
      return objectMapper.readValue(source, BlockProps.class);
    }
  }
}
