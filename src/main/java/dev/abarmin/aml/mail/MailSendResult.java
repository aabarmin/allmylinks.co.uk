package dev.abarmin.aml.mail;

public record MailSendResult(
  MailSendStatus status
) {

  public static MailSendResult ok() {
    return new MailSendResult(MailSendStatus.OK);
  }

  public boolean isOk() {
    return status == MailSendStatus.OK;
  }

  enum MailSendStatus {
    OK,
    ERROR
  }
}
