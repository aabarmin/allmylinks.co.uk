package dev.abarmin.aml.subscribe;

import dev.abarmin.aml.mail.MailSendResult;
import dev.abarmin.aml.mail.MailService;
import dev.abarmin.aml.mail.template.MailTemplate;
import dev.abarmin.aml.mail.template.MailTemplateService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.google.common.base.Preconditions.checkArgument;

@Service
@RequiredArgsConstructor
public class SubscriptionService {
  private final SubscriptionRepository repository;
  private final MailTemplateService templateService;
  private final MailService mailService;

  public boolean subscribe(String email, SubscriptionSource source) {
    final Optional<Subscription> existing = repository.findByEmailAndSource(email, source);
    if (existing.isPresent()) {
      return false;
    }
    final Subscription subscription = repository.save(new Subscription(email, source));

    final MailTemplate<Subscription> template = templateService.subscriptionCreated();
    final MailSendResult sendResult = mailService.send(template, subscription);

    checkArgument(sendResult.isOk(), "Can't send notification about subscription");

    return true;
  }
}
