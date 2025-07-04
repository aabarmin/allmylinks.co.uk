package dev.abarmin.aml.dashboard.repository;

import dev.abarmin.aml.dashboard.domain.Block;
import org.springframework.data.domain.Sort;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;
import java.util.List;

public interface BlockRepository extends CrudRepository<Block, Long> {
  @Query("""
    select b.*
    from page_blocks b
    where b.page_id = :pageId and b.block_deleted = false
    order by b.block_order asc
    """)
  List<Block> findAllVisible(long pageId);

  @Query("""
    select b.*
    from page_blocks b
    where b.page_id = :pageId
    order by b.block_order asc
    """)
  List<Block> findAllByPageId(long pageId);

  Collection<Block> findAllByPageId(long pageId, Sort sort);
}
