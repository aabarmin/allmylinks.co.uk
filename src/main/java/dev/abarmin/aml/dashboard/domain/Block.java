package dev.abarmin.aml.dashboard.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.Instant;

@Table("page_blocks")
public record Block(
  @Id Long id,
  @Column("page_id") long pageId,
  @Column("block_type") BlockType type,
  @Column("block_order") int order,
  @Column("block_deleted") boolean isDeleted,
  @Column("block_props") BlockProps props, // https://stackoverflow.com/questions/64521056/how-to-read-write-postgres-jsonb-type-with-spring-data-jdbc
  @Column("created_at") Instant createdAt,
  @Column("updated_at") Instant updatedAt
) {
}
