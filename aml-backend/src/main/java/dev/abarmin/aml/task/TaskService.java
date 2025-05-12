package dev.abarmin.aml.task;

public interface TaskService {

  AddTaskResponse addTask(String type, Object payload);

  AddTaskResponse addTask(AddTaskRequest request);

}

