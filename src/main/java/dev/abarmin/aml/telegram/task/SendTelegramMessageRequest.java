package dev.abarmin.aml.telegram.task;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class SendTelegramMessageRequest {

  public static final String TASK_TYPE = "SEND_TELEGRAM_MESSAGE_TASK";

  String template;
  long userId;

}
