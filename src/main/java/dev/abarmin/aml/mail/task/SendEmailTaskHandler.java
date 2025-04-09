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
    switch (mailRequest.getTemplate()) {
      case "registrationDone":
        final MailTemplate<User> registrationDone = templateService.registrationDone();
        final User user = userRepository.findById(mailRequest.getObjectId())
          .orElseThrow(() -> new RuntimeException("Unknown user: " + mailRequest.getObjectId()));

        mailService.send(registrationDone, user);
        break;

      default: throw new RuntimeException("Unknown template: " + mailRequest.getTemplate());
    }
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
