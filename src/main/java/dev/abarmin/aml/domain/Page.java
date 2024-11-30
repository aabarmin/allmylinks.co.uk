package dev.abarmin.aml.domain;

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
  @Column("created_at")Instant createdAt
  ) {
}
