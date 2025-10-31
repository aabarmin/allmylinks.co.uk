package dev.abarmin.aml.backoffice.tasks;

import dev.abarmin.aml.task.TaskEntity;
import dev.abarmin.aml.task.TaskId;
import dev.abarmin.aml.task.TaskProcessingQueue;
import dev.abarmin.aml.task.TaskProcessingRequest;
import dev.abarmin.aml.task.TaskRepository;
import dev.abarmin.aml.task.TaskStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class BackofficeTasksController {

  private final TaskRepository taskRepository;
  private final TaskProcessingQueue taskProcessingQueue;

  @GetMapping("/backoffice/tasks")
  public String backofficeTasks() {
    return "backoffice/tasks";
  }

  @GetMapping("/backoffice/tasks/{id}/retry")
  public String retryTask(@PathVariable("id") int taskId) {
    taskProcessingQueue.add(new TaskProcessingRequest(new TaskId(taskId)));
    return "redirect:/backoffice/tasks";
  }

  @GetMapping("/backoffice/tasks/{id}/fail")
  public String failTask(@PathVariable("id") long taskId) {
    taskRepository.findById(taskId)
      .ifPresent(task -> {
        task.setTaskStatus(TaskStatus.FAILED_MANUALLY);
        taskRepository.save(task);
      });

    return "redirect:/backoffice/tasks";
  }

  @ModelAttribute("model")
  private Page<TaskEntity> getTasksPage(
    final @RequestParam(value = "page", defaultValue = "0") int page,
    final @RequestParam(value = "status", defaultValue = "FAILED") TaskStatus status
  ) {
    final PageRequest request = PageRequest
      .of(page, 50)
      .withSort(Sort.by(Sort.Direction.DESC, "lastRunAt"));

    return taskRepository.findAllByTaskStatus(status, request);
  }

}
