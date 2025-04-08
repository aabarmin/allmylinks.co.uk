package dev.abarmin.aml.telegram;

import dev.abarmin.aml.mail.MailSendResult;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.message.Message;
import org.telegram.telegrambots.meta.generics.TelegramClient;
import org.thymeleaf.TemplateEngine;

@Service
@RequiredArgsConstructor
public class TelegramService {

  private final TelegramClient telegramClient;
  private final TemplateEngine templateEngine;

  @SneakyThrows
  public MailSendResult send(final @NonNull String chatId, final @NonNull String text) {
    final SendMessage message = new SendMessage(chatId, text);
    final Message result = telegramClient.execute(message);

    return MailSendResult.ok();
  }

}
