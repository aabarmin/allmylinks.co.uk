package dev.abarmin.aml.e2e;

public class Constants {

  public static String registrationUrl(int serverPort) {
    return String.format("http://localhost:%s/register", serverPort);
  }

}
