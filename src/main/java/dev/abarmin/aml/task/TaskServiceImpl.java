package dev.abarmin.aml.task;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionTemplate;

@Slf4j
@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

  private final TaskRepository taskRepository;
  private final TransactionTemplate transactionTemplate;
  private final TaskProcessingQueue processingQueue;
  private final ObjectMapper objectMapper;

  @Override
  @SneakyThrows
  public AddTaskResponse addTask(String type, Object payload) {
    final AddTaskRequest request = new AddTaskRequest()
      .setTaskType(type)
      .setTaskData(objectMapper.writeValueAsBytes(payload));

    return addTask(request);
  }

  @Override
  public AddTaskResponse addTask(final @NonNull AddTaskRequest request) {
    return transactionTemplate.execute(s -> addTaskInternal(request));
  }

  private AddTaskResponse addTaskInternal(final @NonNull AddTaskRequest request) {
    final TaskEntity entity = toEntity(request);
    final TaskEntity saved = taskRepository.save(entity);

    log.info("Submitted task of type [{}]", saved.getTaskType());
    final AddTaskResponse response = toResponse(saved);

    processingQueue.add(response.getTaskId());
    return response;
  }

  @SneakyThrows
  private TaskEntity toEntity(final AddTaskRequest request) {
    return TaskEntity.builder()
      .taskStatus(TaskStatus.NEW)
      .taskType(request.getTaskType())
      .taskData(request.getTaskData())
      .build();
  }

  static AddTaskResponse toResponse(final @NonNull TaskEntity entity) {
    return AddTaskResponse.builder()
      .taskId(new TaskId(entity.getId()))
      .result(AddTaskResponse.Result.SUCCESS)
      .build();
  }
}
