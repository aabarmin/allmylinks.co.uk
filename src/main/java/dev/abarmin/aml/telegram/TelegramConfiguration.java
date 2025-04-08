package dev.abarmin.aml.telegram;

import dev.abarmin.aml.config.AppConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.telegram.telegrambots.client.okhttp.OkHttpTelegramClient;
import org.telegram.telegrambots.meta.generics.TelegramClient;

@Configuration
public class TelegramConfiguration {

  @Bean
  public TelegramClient telegramClient(final AppConfiguration configuration) {
    final String token = configuration.getBackoffice().getTelegramConfiguration().getBotToken();
    return new OkHttpTelegramClient(token);
  }

}
