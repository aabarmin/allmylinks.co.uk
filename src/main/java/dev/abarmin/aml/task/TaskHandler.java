package dev.abarmin.aml.task;

public interface TaskHandler<T> {

  Class<T> getPayloadType();

  boolean supports(Task task);

  void handle(T payload);
}
