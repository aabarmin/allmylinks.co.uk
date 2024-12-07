package dev.abarmin.aml.profile.converter;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.abarmin.aml.profile.domain.ProfileChangePayload;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;
import org.springframework.data.convert.WritingConverter;

public class ProfileChangePayloadConverter {
  @WritingConverter
  @RequiredArgsConstructor
  public static class Writer implements Converter<ProfileChangePayload, String> {
    private final ObjectMapper objectMapper;

    @Override
    @SneakyThrows
    public String convert(ProfileChangePayload source) {
      return objectMapper.writeValueAsString(source);
    }
  }

  @ReadingConverter
  @RequiredArgsConstructor
  public static class Reader implements Converter<String, ProfileChangePayload> {
    private final ObjectMapper objectMapper;

    @Override
    @SneakyThrows
    public ProfileChangePayload convert(String source) {
      return objectMapper.readValue(source, ProfileChangePayload.class);
    }
  }
}
