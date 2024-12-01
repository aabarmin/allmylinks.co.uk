package dev.abarmin.aml.dashboard.converter;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.abarmin.aml.dashboard.domain.BlockProps;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.postgresql.util.PGobject;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;
import org.springframework.data.convert.WritingConverter;

public class BlockPropsConverter {
  @WritingConverter
  @RequiredArgsConstructor
  public static class Writer implements Converter<BlockProps, PGobject> {
    private final ObjectMapper objectMapper;

    @Override
    @SneakyThrows
    public PGobject convert(BlockProps source) {
      final PGobject pGobject = new PGobject();
      pGobject.setType("jsonb");
      pGobject.setValue(objectMapper.writeValueAsString(source));
      return pGobject;
    }
  }

  @ReadingConverter
  @RequiredArgsConstructor
  public static class Reader implements Converter<PGobject, BlockProps> {
    private final ObjectMapper objectMapper;

    @Override
    @SneakyThrows
    public BlockProps convert(PGobject source) {
      return objectMapper.readValue(source.getValue(), BlockProps.class);
    }
  }
}
