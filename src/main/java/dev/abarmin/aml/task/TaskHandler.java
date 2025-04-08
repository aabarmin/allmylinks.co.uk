package dev.abarmin.aml.task;

public interface TaskHandler {

  boolean supports(Task task);

  void handle(Task task);
}
