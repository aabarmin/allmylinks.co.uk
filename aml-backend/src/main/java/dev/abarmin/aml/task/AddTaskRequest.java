package dev.abarmin.aml.task;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class AddTaskRequest {

  String taskType;
  byte[] taskData;

}
