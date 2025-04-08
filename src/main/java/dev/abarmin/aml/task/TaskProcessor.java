package dev.abarmin.aml.task;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionTemplate;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
public class TaskProcessor {

  private final TaskRepository taskRepository;
  private final TaskExecutorRegistry executorRegistry;
  private final TaskProcessingQueue processingQueue;
  private final ObjectMapper objectMapper;
  private final TransactionTemplate transactionTemplate;

  public TaskProcessor(TaskProcessingQueue processingQueue,
                       TaskExecutorRegistry executorRegistry,
                       TaskRepository taskRepository,
                       ObjectMapper objectMapper,
                       TransactionTemplate transactionTemplate) {

    this.processingQueue = processingQueue;
    this.taskRepository = taskRepository;
    this.executorRegistry = executorRegistry;
    this.objectMapper = objectMapper;
    this.transactionTemplate = transactionTemplate;

    final Runnable taskConsumer = () -> {
      while (true) {
        final TaskProcessingQueue.TaskProcessingRequest request = processingQueue.takeOrBlock();
        process(request);
      }
    };

    final Thread thread = new Thread(taskConsumer);
    thread.setName("Task processor");
    thread.start();

    log.info("Task processor started");
  }

  @Scheduled(fixedRate = 1, timeUnit = TimeUnit.DAYS)
  void cleanup() {
    log.info("Starting cleanup of tasks");

    final Instant periodStart = Instant.now().minus(Duration.ofDays(5));
    final var toDelete = taskRepository.findAllByTaskStatusAndLastRunAtBefore(TaskService.TaskStatus.COMPLETED, periodStart);

    log.info("Deleting completed tasks older than {}, to delete {}", periodStart, toDelete.size());
    taskRepository.deleteAll(toDelete);
  }

  @Scheduled(fixedRate = 5, timeUnit = TimeUnit.MINUTES)
  void restart() {
    log.info("Starting restart");
    final var toRestartNew = taskRepository.findAllByTaskStatus(TaskService.TaskStatus.NEW);

    log.info("Restarting {} new tasks", toRestartNew.size());
    toRestartNew.stream()
      .map(TaskEntity::getTaskId)
      .map(TaskProcessingQueue.TaskProcessingRequest::new)
      .forEach(processingQueue::add);

    final var toRestartFailed = taskRepository.findAllByTaskStatus(TaskService.TaskStatus.FAILED);
    log.info("Restarting {} failed tasks", toRestartFailed.size());
    toRestartFailed.stream()
      .map(TaskEntity::getTaskId)
      .map(TaskProcessingQueue.TaskProcessingRequest::new)
      .forEach(processingQueue::add);
  }

  private void process(final @NonNull TaskProcessingQueue.TaskProcessingRequest request) {
    transactionTemplate
      .execute(status -> taskRepository.findById(request.getTaskId().getValue()))
      .ifPresentOrElse(this::process, () -> {
        if (request.getAttempt() > 5) {
          log.error("Task processing failed, attempt {} of task {}", request.getAttempt(), request.getTaskId());
          return;
        }
        processingQueue.add(request.nextAttempt());
      });
  }

  private <T> void process(final @NonNull Task task) {
    log.info("[TaskId: {}] Start processing task", task.getTaskId());

    if (task.isProcessed()) {
      log.info("[TaskId: {}] Task is already processed", task.getTaskId());
      return;
    }

    try {
      task.setTaskStatus(TaskService.TaskStatus.IN_PROGRESS);
      task.setException("");
      task.setLastRunAt(Instant.now());
      task.setExecutionAttempts(task.getExecutionAttempts() + 1);

      final TaskHandler<T> handler = executorRegistry.getHandler(task);
      final T payload = objectMapper.readValue(task.getTaskData(), handler.getPayloadType());
      handler.handle(payload);

      task.setTaskStatus(TaskService.TaskStatus.COMPLETED);
      log.info("[TaskId: {}] Task completed", task.getTaskId());

    } catch (Exception e) {

      task.setTaskStatus(TaskService.TaskStatus.FAILED);
      task.setException(ExceptionUtils.getStackTrace(e));

      log.warn("[TaskId: {}] Task failed", task.getTaskId(), e);
    } finally {
      if (task instanceof TaskEntity entity) {
        taskRepository.save(entity);
      }
    }
  }
}
