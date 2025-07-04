package dev.abarmin.aml;

import dev.abarmin.aml.task.TaskRepository;
import dev.abarmin.aml.task.TaskStatus;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Duration;

import static org.awaitility.Awaitility.await;

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public abstract class BaseIntegrationTest {

  @Autowired
  TaskRepository taskRepository;

  @BeforeEach
  void setUp() {
    taskRepository.deleteAll();
  }

  @AfterEach
  void tearDown() {
    await()
      .atMost(Duration.ofSeconds(5))
      .pollInterval(Duration.ofMillis(500))
      .until(() -> taskRepository.findAllByTaskStatus(TaskStatus.NEW).size() == 0);
  }
}
