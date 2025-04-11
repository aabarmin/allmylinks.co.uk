package dev.abarmin.aml.profile.converter;

import dev.abarmin.aml.file.FileId;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;
import org.springframework.data.convert.WritingConverter;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class FileIdJdbcConverter {

  @WritingConverter
  public static class Writer implements Converter<FileId, String> {

    @Override
    public String convert(FileId source) {
      return source.asString();
    }
  }

  @ReadingConverter
  public static class Reader implements Converter<String, FileId> {
    @Override
    public FileId convert(String source) {
      return FileId.of(source);
    }
  }
}
