package dev.abarmin.aml.profile.converter;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.abarmin.aml.profile.domain.ProfileChangePayload;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.postgresql.util.PGobject;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;
import org.springframework.data.convert.WritingConverter;

public class ProfileChangePayloadConverter {
  @WritingConverter
  @RequiredArgsConstructor
  public static class Writer implements Converter<ProfileChangePayload, PGobject> {
    private final ObjectMapper objectMapper;

    @Override
    @SneakyThrows
    public PGobject convert(ProfileChangePayload source) {
      final PGobject pGobject = new PGobject();
      pGobject.setType("jsonb");
      pGobject.setValue(objectMapper.writeValueAsString(source));
      return pGobject;
    }
  }

  @ReadingConverter
  @RequiredArgsConstructor
  public static class Reader implements Converter<PGobject, ProfileChangePayload> {
    private final ObjectMapper objectMapper;

    @Override
    @SneakyThrows
    public ProfileChangePayload convert(PGobject source) {
      return objectMapper.readValue(source.getValue(), ProfileChangePayload.class);
    }
  }
}
