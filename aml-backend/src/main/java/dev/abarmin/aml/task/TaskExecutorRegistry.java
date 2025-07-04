package dev.abarmin.aml.task;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
@RequiredArgsConstructor
public class TaskExecutorRegistry {

  private final Collection<TaskHandler> handlers;

  public TaskHandler getHandler(final Task task) {
    return handlers.stream()
      .filter(handler -> handler.supports(task))
      .findFirst()
      .orElseThrow(() -> new RuntimeException("No handler found for task " + task));
  }
}
