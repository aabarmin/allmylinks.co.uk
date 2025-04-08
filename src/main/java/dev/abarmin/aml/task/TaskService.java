package dev.abarmin.aml.task;

import lombok.Builder;
import lombok.Data;
import lombok.Value;
import lombok.experimental.Accessors;

public interface TaskService {

  @Data
  @Accessors(chain = true)
  class AddTaskRequest {

    private String taskType;
    private byte[] taskData;

  }

  @Value
  class TaskId {

    long value;

    @Override
    public String toString() {
      return String.valueOf(value);
    }
  }

  @Value
  @Builder
  class AddTaskResponse {

    TaskId taskId;
    Result result;

    public enum Result {
      SUCCESS,
      FAILURE
    }
  }

  enum TaskStatus {
    NEW,
    IN_PROGRESS,
    COMPLETED,
    FAILED
  }

  AddTaskResponse addTask(String type, Object payload);

  AddTaskResponse addTask(AddTaskRequest request);

}

