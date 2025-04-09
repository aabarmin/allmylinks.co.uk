package dev.abarmin.aml.mail.task;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class SendEmailRequest {

  public static final String TASK_TYPE = "SEND_EMAIL_TASK";

  String template;
  long objectId;

}
