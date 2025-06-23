package dev.abarmin.aml.utils;

import dev.abarmin.aml.registration.domain.User;
import lombok.experimental.UtilityClass;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;

import java.time.Instant;

import static dev.abarmin.aml.utils.RandomUtils.email;

@UtilityClass
public class TestUserFactory {
  public User build() {
    return new User(
      RandomUtils.secure().randomLong(),
      email(),
      RandomStringUtils.secure().nextAlphabetic(32),
      Instant.now()
    );
  }
}
