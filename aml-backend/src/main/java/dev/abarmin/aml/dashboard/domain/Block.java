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
  @Column("block_props") BlockProps props,
  @Column("created_at") Instant createdAt,
  @Column("updated_at") Instant updatedAt
) {
  public Block(long pageId, BlockType type, int order, BlockProps props) {
    this(
      null,
      pageId,
      type,
      order,
      false,
      props,
      Instant.now(),
      null
    );
  }

  public Block withProps(BlockProps props) {
    return new Block(
      id,
      pageId,
      type,
      order,
      isDeleted,
      props,
      createdAt,
      Instant.now()
    );
  }

  public Block withOrder(int newOrder) {
    return new Block(
      id,
      pageId,
      type,
      newOrder,
      isDeleted,
      props,
      createdAt,
      Instant.now()
    );
  }

  public Block withDeleted() {
    return new Block(
      id,
      pageId,
      type,
      order,
      true,
      props,
      createdAt,
      Instant.now()
    );
  }
}
