package dev.abarmin.aml.task;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import java.time.Instant;
import java.util.Collection;

public interface TaskRepository extends CrudRepository<TaskEntity, Long> {

  Collection<TaskEntity> findAllByTaskStatusAndLastRunAtBefore(TaskStatus status, Instant lastRunAt);

  Collection<TaskEntity> findAllByTaskStatus(TaskStatus status);

  long countAllByTaskStatus(TaskStatus status);

  Page<TaskEntity> findAllByTaskStatus(TaskStatus status, Pageable pageable);
}
