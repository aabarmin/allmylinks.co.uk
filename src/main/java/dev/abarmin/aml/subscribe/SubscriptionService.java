package dev.abarmin.aml.subscribe;

import dev.abarmin.aml.mail.task.SendEmailRequest;
import dev.abarmin.aml.task.TaskService;
import dev.abarmin.aml.telegram.task.SendTelegramMessageRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SubscriptionService {

  private final SubscriptionRepository repository;
  private final TaskService taskService;

  public boolean subscribe(String email, SubscriptionSource source) {
    final Optional<Subscription> existing = repository.findByEmailAndSource(email, source);
    if (existing.isPresent()) {
      return false;
    }
    final Subscription subscription = repository.save(new Subscription(email, source));

    taskService.addTask(SendTelegramMessageRequest.TASK_TYPE, SendTelegramMessageRequest.builder()
      .template("subscriptionCreated")
      .objectId(subscription.id())
      .build());

    return true;
  }
}
