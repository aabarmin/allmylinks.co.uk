package dev.abarmin.aml.task;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class AddTaskResponse {

  TaskId taskId;
  Result result;

  public enum Result {
    SUCCESS,
    FAILURE
  }
}
