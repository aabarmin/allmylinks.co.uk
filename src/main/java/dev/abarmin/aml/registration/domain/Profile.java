package dev.abarmin.aml.registration.domain;

import dev.abarmin.aml.file.FileId;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.Instant;

@Table("user_profiles")
public record Profile(
  @Id Long id,
  @Column("user_id") long userId,
  @Column("profile_link") String link,
  @Column("profile_qr") FileId qrCode,
  @Column("created_at") Instant createdAt,
  @Column("updated_at") Instant updatedAt
) {

  public Profile withQrCode(FileId qrCode) {
    return new Profile(id, userId, link, qrCode, createdAt, Instant.now());
  }
}
