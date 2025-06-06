package dev.abarmin.aml.task;

import org.springframework.data.repository.CrudRepository;

import java.time.Instant;
import java.util.Collection;

public interface TaskRepository extends CrudRepository<TaskEntity, Long> {

  Collection<TaskEntity> findAllByTaskStatusAndLastRunAtBefore(TaskStatus status, Instant lastRunAt);

  Collection<TaskEntity> findAllByTaskStatus(TaskStatus status);

}
