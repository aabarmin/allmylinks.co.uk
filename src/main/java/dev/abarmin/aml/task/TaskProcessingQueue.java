package dev.abarmin.aml.task;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.SneakyThrows;
import lombok.Value;
import org.springframework.stereotype.Component;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

@Component
public class TaskProcessingQueue {

  private final BlockingQueue<TaskProcessingRequest> queue = new LinkedBlockingQueue<>();

  @Value
  @AllArgsConstructor
  static class TaskProcessingRequest {
    int attempt;
    TaskService.TaskId taskId;

    public TaskProcessingRequest(final TaskService.TaskId taskId) {
      this.taskId = taskId;
      this.attempt = 0;
    }

    public TaskProcessingRequest nextAttempt() {
      return new TaskProcessingRequest(attempt + 1, taskId);
    }
  }

  public void add(final @NonNull TaskProcessingRequest request) {
    queue.add(request);
  }

  public int length() {
    return queue.size();
  }

  @SneakyThrows
  public TaskProcessingRequest takeOrBlock() {
    return queue.take();
  }
}
