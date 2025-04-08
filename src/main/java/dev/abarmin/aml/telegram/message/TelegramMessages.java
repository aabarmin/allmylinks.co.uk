package dev.abarmin.aml.telegram.message;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TelegramMessages {

  public static final String NEW_USER_REGISTERED = """
    New user registered, name: ${name}, email: ${email}
    """;

}
