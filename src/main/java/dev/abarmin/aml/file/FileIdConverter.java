package dev.abarmin.aml.file;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import lombok.experimental.UtilityClass;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;

@UtilityClass
public class FileIdConverter {
  public static class Serializer extends JsonSerializer<FileId> {

    @Override
    public void serialize(FileId value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
      gen.writeString(value.asString());
    }
  }

  public static class Deserializer extends JsonDeserializer<FileId> {

    @Override
    public FileId deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JacksonException {
      final String stringValue = p.getValueAsString();
      if (StringUtils.isEmpty(stringValue)) {
        return null;
      }
      return FileId.of(stringValue);
    }
  }
}
