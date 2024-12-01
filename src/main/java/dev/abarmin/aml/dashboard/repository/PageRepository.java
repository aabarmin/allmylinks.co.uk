package dev.abarmin.aml.dashboard.repository;

import dev.abarmin.aml.dashboard.domain.Page;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;
import java.util.Optional;

public interface PageRepository extends CrudRepository<Page, Long> {
  Collection<Page> findAllByProfileId(long profileId);
  Optional<Page> findByIdAndProfileId(long pageId, long profileId);
}
