package dev.abarmin.aml.dashboard.repository;

import dev.abarmin.aml.dashboard.domain.Block;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;

public interface BlockRepository extends CrudRepository<Block, Long> {
  Collection<Block> findAllByPageId(long pageId);
  Collection<Block> findAllByPageId(long pageId, Sort sort);
}
