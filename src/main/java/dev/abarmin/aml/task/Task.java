package dev.abarmin.aml.task;

import java.time.Instant;

public interface Task {

  TaskService.TaskId getTaskId();
  String getTaskType();
  byte[] getTaskData();
  boolean isProcessed();

  void setTaskStatus(TaskService.TaskStatus status);
  void setException(String exception);
  void setLastRunAt(Instant lastRun);
  void setExecutionAttempts(int attempts);
  int getExecutionAttempts();
}
