package dev.abarmin.aml.e2e.fixture;

import dev.abarmin.aml.e2e.page.registration.RegistrationForm;
import lombok.experimental.UtilityClass;
import org.apache.commons.lang3.RandomStringUtils;

import java.util.Locale;

@UtilityClass
public class RegistrationFormFixture {

  private static final RandomStringUtils RSU = RandomStringUtils.secure();

  public static RegistrationForm forSuccessRegistration() {
    return new RegistrationForm(
      RSU.nextAlphabetic(10),
      RSU.nextAlphabetic(10) + "@test.com",
      RSU.nextAlphanumeric(10),
      RSU.nextAlphabetic(10).toLowerCase(Locale.ROOT),
      true
    );
  }

}
