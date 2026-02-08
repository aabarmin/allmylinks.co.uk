package dev.abarmin.aml.backoffice.dashboard;

import dev.abarmin.aml.backoffice.dashboard.model.BackofficeProfileChangeRequestStats;
import dev.abarmin.aml.backoffice.dashboard.model.BackofficeTaskStats;
import dev.abarmin.aml.profile.ProfileChangeRepository;
import dev.abarmin.aml.profile.domain.ProfileChangeStatus;
import dev.abarmin.aml.registration.repository.UserRepository;
import dev.abarmin.aml.task.TaskRepository;
import dev.abarmin.aml.task.TaskStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import static dev.abarmin.aml.config.BasePaths.BACKOFFICE;

@Controller
@RequiredArgsConstructor
@RequestMapping(BACKOFFICE)
public class BackofficeHomeController {
  private final UserRepository userRepository;
  private final TaskRepository taskRepository;
  private final ProfileChangeRepository profileChangeRepository;

  @ModelAttribute("model")
  public BackofficeDashboard model() {
    return BackofficeDashboard.builder()
      .totalUsers(userRepository.count())
      .taskStats(getTaskStats())
      .changeRequestStats(getChangeRequestStats())
      .build();
  }

  @GetMapping
  public String backofficeHome() {
    return "backoffice/home";
  }

  private BackofficeTaskStats getTaskStats() {
    return BackofficeTaskStats.builder()
      .total(taskRepository.count())
      .waiting(taskRepository.countAllByTaskStatus(TaskStatus.NEW))
      .failed(taskRepository.countAllByTaskStatus(TaskStatus.FAILED))
      .build();
  }

  private BackofficeProfileChangeRequestStats getChangeRequestStats() {
    return BackofficeProfileChangeRequestStats.builder()
      .total(profileChangeRepository.count())
      .waiting(profileChangeRepository.countAllByChangeStatus(ProfileChangeStatus.CREATED))
      .build();
  }
}
