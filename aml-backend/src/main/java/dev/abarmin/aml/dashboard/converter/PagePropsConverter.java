package dev.abarmin.aml.dashboard.converter;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.abarmin.aml.dashboard.domain.PageProps;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.postgresql.util.PGobject;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;
import org.springframework.data.convert.WritingConverter;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PagePropsConverter {

  @WritingConverter
  @RequiredArgsConstructor
  public static class Writer implements Converter<PageProps, PGobject> {
    private final ObjectMapper objectMapper;

    @Override
    @SneakyThrows
    public PGobject convert(PageProps source) {
      final PGobject object = new PGobject();
      object.setType("jsonb");
      object.setValue(objectMapper.writeValueAsString(source));
      return object;
    }
  }

  @ReadingConverter
  @RequiredArgsConstructor
  public static class Reader implements Converter<PGobject, PageProps> {
    private final ObjectMapper objectMapper;

    @Override
    @SneakyThrows
    public PageProps convert(PGobject source) {
      final String content = source.getValue();
      return objectMapper.readValue(content, PageProps.class);
    }
  }
}
