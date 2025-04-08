package dev.abarmin.aml.task;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.abarmin.aml.BaseIntegrationTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.time.Duration;
import java.time.Instant;

import static org.assertj.core.api.Assertions.assertThat;
import static org.awaitility.Awaitility.await;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class TaskServiceImplTest extends BaseIntegrationTest {

  @Autowired
  TaskService taskService;

  @Autowired
  TaskRepository taskRepository;

  @Autowired
  ObjectMapper objectMapper;

  @Autowired
  TaskProcessingQueue processingQueue;

  @MockitoBean
  TaskHandler taskHandler;

  @BeforeEach
  void setUp() {
    when(taskHandler.supports(any(Task.class))).thenReturn(true);
  }

  @Test
  void addTask_whenSubmitted_thenSavedToDatabase() throws Exception {
    final TaskService.AddTaskRequest request = new TaskService.AddTaskRequest()
      .setTaskType("TEST")
      .setTaskData(objectMapper.writeValueAsBytes(Instant.now()));

    final TaskService.AddTaskResponse response = taskService.addTask(request);

    assertThat(response).isNotNull();
    assertThat(response.getResult()).isEqualTo(TaskService.AddTaskResponse.Result.SUCCESS);

    final TaskEntity entity = taskRepository.findById(response.getTaskId().getValue()).orElseThrow();

    assertThat(entity.getTaskType()).isEqualTo(request.getTaskType());

    await()
      .atMost(Duration.ofSeconds(5))
      .until(() -> processingQueue.length() == 0);
  }


}
