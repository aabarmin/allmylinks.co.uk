package dev.abarmin.aml.sitemap.model;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class SitemapRecordDateSerializer extends JsonSerializer<ZonedDateTime> {

  private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssXXX");

  @Override
  public void serialize(ZonedDateTime value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
    gen.writeString(formatter.format(value));
  }
}
