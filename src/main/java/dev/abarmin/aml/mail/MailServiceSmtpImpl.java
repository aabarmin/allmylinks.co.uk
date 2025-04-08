package dev.abarmin.aml.mail;

import dev.abarmin.aml.mail.extractor.MailParams;
import dev.abarmin.aml.mail.template.MailTemplate;
import jakarta.mail.internet.MimeMessage;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Service
@RequiredArgsConstructor
public class MailServiceSmtpImpl implements MailService {
  private final JavaMailSender mailSender;
  private final TemplateEngine templateEngine;

  @Override
  @SneakyThrows
  public <T> MailSendResult send(@NonNull MailTemplate<T> template, @NonNull T source) {
    // generate mail body based on the template provided
    final MailParams mailParams = template.extractor().apply(source);
    final Context templateContext = new Context();
    templateContext.setVariables(mailParams.templateParams());
    final String mailBody = templateEngine.process(template.templateName(), templateContext);
    // create a message
    final MimeMessage message = mailSender.createMimeMessage();
    final MimeMessageHelper helper = new MimeMessageHelper(message);
    helper.setFrom(template.fromEmail());
    helper.setTo(mailParams.to());
    helper.setSubject(template.subject());
    helper.setText(mailBody, true);
    // actually send the email
    mailSender.send(message);

    return MailSendResult.ok();
  }
}
