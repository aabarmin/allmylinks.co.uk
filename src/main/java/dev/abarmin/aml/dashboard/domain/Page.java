package dev.abarmin.aml.dashboard.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.Instant;

@Table("pages")
public record Page(
  @Id Long id,
  @Column("profile_id") long profileId,
  @Column("page_title") String title,
  @Column("page_home") boolean isHome,
  @Column("page_deleted") boolean isDeleted,
  @Column("page_props") PageProps pageProps,
  @Column("created_at")Instant createdAt,
  @Column("updated_at")Instant updatedAt
  ) {
}
