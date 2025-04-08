package dev.abarmin.aml.task;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.abarmin.aml.BaseIntegrationTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.Duration;
import java.time.Instant;

import static org.assertj.core.api.Assertions.assertThat;
import static org.awaitility.Awaitility.await;

class TaskServiceImplTest extends BaseIntegrationTest {

  @Autowired
  TaskService taskService;

  @Autowired
  TaskRepository taskRepository;

  @Autowired
  ObjectMapper objectMapper;

  @Autowired
  TaskProcessingQueue processingQueue;

  @Test
  void addTask_whenSubmitted_thenSavedToDatabase() throws Exception {
    final AddTaskRequest request = new AddTaskRequest()
      .setTaskType("TEST")
      .setTaskData(objectMapper.writeValueAsBytes(Instant.now()));

    final AddTaskResponse response = taskService.addTask(request);

    assertThat(response).isNotNull();
    assertThat(response.getResult()).isEqualTo(AddTaskResponse.Result.SUCCESS);

    final TaskEntity entity = taskRepository.findById(response.getTaskId().getValue()).orElseThrow();

    assertThat(entity.getTaskType()).isEqualTo(request.getTaskType());

    await()
      .atMost(Duration.ofSeconds(5))
      .until(() -> processingQueue.length() == 0);
  }


}
