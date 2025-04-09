package dev.abarmin.aml.task;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.Instant;

@Data
@Builder
@Table("tasks")
public class TaskEntity implements Task {

  @Id
  @Column("id")
  private long id;

  @Column("task_type")
  private String taskType;

  @Column("task_data")
  private byte[] taskData;

  @Column("task_status")
  private TaskStatus taskStatus;

  @Builder.Default
  @Column("execution_attempts")
  private int executionAttempts = 0;

  @Column("last_run_at")
  private Instant lastRunAt;

  @Column("execution_exception")
  private String exception;

  @Builder.Default
  @Column("created_at")
  private Instant createdAt = Instant.now();

  @Column("updated_at")
  private Instant updatedAt;

  @Override
  public boolean isProcessed() {
    return taskStatus == TaskStatus.COMPLETED;
  }

  @Override
  public TaskId getTaskId() {
    return new TaskId(id);
  }
}
