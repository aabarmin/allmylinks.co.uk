package dev.abarmin.aml.backoffice.dashboard;

import dev.abarmin.aml.registration.repository.UserRepository;
import dev.abarmin.aml.task.TaskRepository;
import dev.abarmin.aml.task.TaskStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
@RequiredArgsConstructor
public class BackofficeHomeController {
  private final UserRepository userRepository;
  private final TaskRepository taskRepository;

  @ModelAttribute("model")
  public BackofficeDashboard model() {
    return BackofficeDashboard.builder()
      .totalUsers(userRepository.count())
      .taskStats(getTaskStats())
      .build();
  }

  @GetMapping("/backoffice")
  public String backofficeHome() {
    return "backoffice/home";
  }

  private BackofficeTaskStats getTaskStats() {
    return new BackofficeTaskStats()
      .setTotal(taskRepository.count())
      .setWaiting(taskRepository.countAllByTaskStatus(TaskStatus.NEW))
      .setFailed(taskRepository.countAllByTaskStatus(TaskStatus.FAILED));
  }
}
