package dev.abarmin.aml.telegram.task;

import dev.abarmin.aml.config.AppConfiguration;
import dev.abarmin.aml.registration.domain.User;
import dev.abarmin.aml.registration.repository.UserRepository;
import dev.abarmin.aml.task.Task;
import dev.abarmin.aml.task.TaskHandler;
import dev.abarmin.aml.telegram.TelegramService;
import dev.abarmin.aml.telegram.message.TelegramMessages;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@RequiredArgsConstructor
public class SendTelegramMessageTaskHandler implements TaskHandler<SendTelegramMessageRequest> {

  private final UserRepository userRepository;
  private final AppConfiguration configuration;
  private final TelegramService telegramService;

  @Override
  public Class<SendTelegramMessageRequest> getPayloadType() {
    return SendTelegramMessageRequest.class;
  }

  @Override
  public boolean supports(Task task) {
    return StringUtils.equalsIgnoreCase(task.getTaskType(), SendTelegramMessageRequest.TASK_TYPE);
  }

  @Override
  public void handle(SendTelegramMessageRequest request) {
    final String template = switch (request.getTemplate()) {
      case "registrationDoneAdmin": yield TelegramMessages.NEW_USER_REGISTERED;
      default: throw new RuntimeException("Unknown mail template: " + request.getTemplate());
    };
    final User user = userRepository.findById(request.getUserId())
      .orElseThrow(() -> new RuntimeException("Unknown user: " + request.getUserId()));
    final String payload = replace(template, Map.of(
      "name", user.userName(),
      "email", user.email()
    ));
    final String chatId = configuration.getBackoffice().getTelegramConfiguration().getAdminChatId();

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
