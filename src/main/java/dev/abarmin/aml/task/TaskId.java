package dev.abarmin.aml.task;

import lombok.Value;

@Value
public class TaskId {

  long value;

  @Override
  public String toString() {
    return String.valueOf(value);
  }
}
