package dev.abarmin.aml.file;

public record FileId(
  Storage storage,
  String filePath
) {
  public static FileId disk(String filePath) {
    return new FileId(Storage.DISK, filePath);
  }

  public String asString() {
    return storage.name() + ":" + filePath;
  }

  @Override
  public String toString() {
    return asString();
  }
}
