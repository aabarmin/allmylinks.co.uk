package dev.abarmin.aml.file;

import lombok.RequiredArgsConstructor;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
@RequiredArgsConstructor
public class FileIdFormatter implements Formatter<FileId> {
  private final FileService fileService;

  @Override
  public FileId parse(String text, Locale locale) {
    throw new UnsupportedOperationException("Not implemented");
  }

  @Override
  public String print(FileId object, Locale locale) {
    return fileService.getPublicUrl(object);
  }
}
