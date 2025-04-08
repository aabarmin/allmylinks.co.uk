package dev.abarmin.aml.task;

import lombok.NonNull;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

@Component
public class TaskProcessingQueue {

  private final BlockingQueue<TaskService.TaskId> queue = new LinkedBlockingQueue<>();

  public void add(final @NonNull TaskService.TaskId request) {
    queue.add(request);
  }

  public int length() {
    return queue.size();
  }

  @SneakyThrows
  public TaskService.TaskId takeOrBlock() {
    return queue.take();
  }
}
