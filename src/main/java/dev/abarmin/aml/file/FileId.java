package dev.abarmin.aml.file;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.NonNull;

@JsonSerialize(using = FileIdConverter.Serializer.class)
@JsonDeserialize(using = FileIdConverter.Deserializer.class)
public record FileId(
  Storage storage,
  String filePath
) {
  public static FileId disk(@NonNull String filePath) {
    return new FileId(Storage.DISK, filePath);
  }

  public static FileId resource(@NonNull String resourcePath) {
    return new FileId(Storage.RESOURCE, resourcePath);
  }

  public static FileId of(@NonNull String value) {
    final String[] parts = value.split(":");
    if (parts.length != 2) {
      throw new IllegalArgumentException("Invalid file id: " + value);
    }

    return new FileId(Storage.valueOf(parts[0]), parts[1]);
  }

  public String asString() {
    return storage.name() + ":" + filePath;
  }

  @Override
  public String toString() {
    return asString();
  }
}
