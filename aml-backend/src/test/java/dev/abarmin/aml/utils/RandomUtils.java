package dev.abarmin.aml.utils;

import lombok.experimental.UtilityClass;
import org.apache.commons.lang3.RandomStringUtils;

@UtilityClass
public class RandomUtils {
  public static String email() {
    return RandomStringUtils.secure().nextAlphabetic(5) + "@test.com";
  }
}
