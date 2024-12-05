package dev.abarmin.aml.file;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.Instant;

@Table("files")
public record FileEntity(
  @Id Long id,
  @Column("user_id") long userId,
  @Column("file_storage") Storage storage,
  @Column("file_path") String path,
  @Column("created_at") Instant createdAt,
  @Column("updated_at") Instant updatedAt
) {
  public FileEntity(long userId, FileId fileId) {
    this(
      null,
      userId,
      fileId.storage(),
      fileId.filePath(),
      Instant.now(),
      null
    );
  }
}
