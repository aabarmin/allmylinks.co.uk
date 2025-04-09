package dev.abarmin.aml.task;

import lombok.AllArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor
public class TaskProcessingRequest {
  int attempt;
  TaskId taskId;

  public TaskProcessingRequest(final TaskId taskId) {
    this.taskId = taskId;
    this.attempt = 0;
  }

  public TaskProcessingRequest nextAttempt() {
    return new TaskProcessingRequest(attempt + 1, taskId);
  }
}
