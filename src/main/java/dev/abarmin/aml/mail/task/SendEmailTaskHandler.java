package dev.abarmin.aml.mail.task;

import dev.abarmin.aml.mail.MailService;
import dev.abarmin.aml.mail.template.MailTemplate;
import dev.abarmin.aml.mail.template.MailTemplateService;
import dev.abarmin.aml.registration.domain.User;
import dev.abarmin.aml.registration.repository.UserRepository;
import dev.abarmin.aml.task.Task;
import dev.abarmin.aml.task.TaskHandler;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SendEmailTaskHandler implements TaskHandler<SendEmailRequest> {

  private final MailTemplateService templateService;
  private final UserRepository userRepository;
  private final MailService mailService;

  @Override
  public void handle(SendEmailRequest mailRequest) {
    final MailTemplate<User> template = switch (mailRequest.getTemplate()) {
      case "registrationDone": yield templateService.registrationDone();
      default: throw new RuntimeException("Unknown mail template: " + mailRequest.getTemplate());
    };

    final User user = userRepository.findById(mailRequest.getUserId())
      .orElseThrow(() -> new RuntimeException("Unknown user: " + mailRequest.getUserId()));

    mailService.send(template, user);
  }

  @Override
  public boolean supports(Task task) {
    return StringUtils.equalsIgnoreCase(task.getTaskType(), SendEmailRequest.TASK_TYPE);
  }

  @Override
  public Class<SendEmailRequest> getPayloadType() {
    return SendEmailRequest.class;
  }
}
