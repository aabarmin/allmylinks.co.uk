package dev.abarmin.aml.telegram.task;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.abarmin.aml.config.AppConfiguration;
import dev.abarmin.aml.profile.ProfileChangeRepository;
import dev.abarmin.aml.registration.domain.User;
import dev.abarmin.aml.registration.repository.UserRepository;
import dev.abarmin.aml.subscribe.Subscription;
import dev.abarmin.aml.subscribe.SubscriptionRepository;
import dev.abarmin.aml.task.Task;
import dev.abarmin.aml.task.TaskHandler;
import dev.abarmin.aml.telegram.TelegramService;
import dev.abarmin.aml.telegram.message.TelegramMessages;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@RequiredArgsConstructor
public class SendTelegramMessageTaskHandler implements TaskHandler<SendTelegramMessageRequest> {

  private final UserRepository userRepository;
  private final SubscriptionRepository subscriptionRepository;
  private final ProfileChangeRepository profileChangeRepository;
  private final AppConfiguration configuration;
  private final TelegramService telegramService;
  private final ObjectMapper objectMapper;

  @Override
  public Class<SendTelegramMessageRequest> getPayloadType() {
    return SendTelegramMessageRequest.class;
  }

  @Override
  public boolean supports(Task task) {
    return StringUtils.equalsIgnoreCase(task.getTaskType(), SendTelegramMessageRequest.TASK_TYPE);
  }

  @Override
  @SneakyThrows
  public void handle(SendTelegramMessageRequest request) {
    final String chatId = configuration.getBackoffice().getTelegramConfiguration().getAdminChatId();
    final String payload = switch (request.getTemplate()) {
      case "registrationDoneAdmin":
        final User user = userRepository.findById(request.getObjectId())
          .orElseThrow(() -> new RuntimeException("Unknown user: " + request.getObjectId()));

        yield replace(TelegramMessages.NEW_USER_REGISTERED, Map.of(
          "name", user.userName(),
          "email", user.email()
        ));

      case "subscriptionCreated":
        final Subscription subscription = subscriptionRepository.findById(request.getObjectId())
          .orElseThrow(() -> new RuntimeException("Unknown subscription: " + request.getObjectId()));

        yield replace(TelegramMessages.NEW_SUBSCRIPTION_ADDED, Map.of(
          "email", subscription.email()
        ));

      case "profileChangeRequestCreated":
        final var changeRequest = profileChangeRepository.findById(request.getObjectId())
          .orElseThrow(() -> new RuntimeException("Unknown change request: " + request.getObjectId()));

        final User requestOwner = userRepository.findById(changeRequest.userId())
          .orElseThrow(() -> new RuntimeException("Unknown request owner: " + changeRequest.userId()));

        yield replace(TelegramMessages.NEW_CHANGE_REQUEST_ADDED, Map.of(
          "email", requestOwner.email(),
          "requestType", changeRequest.changeType().name(),
          "payload", objectMapper.writeValueAsString(changeRequest.changePayload())
        ));

      default: throw new RuntimeException("Unknown mail template: " + request.getTemplate());
    };

    telegramService.send(chatId, payload);
  }

  private String replace(final String input, final Map<String, String> values) {
    String processed = input;
    for (Map.Entry<String, String> entry : values.entrySet()) {
      final String template = "${" + entry.getKey() + "}";
      processed = processed.replace(template, entry.getValue());
    }
    return processed;
  }
}
