package dev.abarmin.aml.registration;

import dev.abarmin.aml.dashboard.domain.Page;
import dev.abarmin.aml.dashboard.repository.PageRepository;
import dev.abarmin.aml.mail.MailSendResult;
import dev.abarmin.aml.mail.MailService;
import dev.abarmin.aml.mail.MailTemplate;
import dev.abarmin.aml.mail.MailTemplateService;
import dev.abarmin.aml.registration.domain.Account;
import dev.abarmin.aml.registration.domain.AccountType;
import dev.abarmin.aml.registration.domain.Profile;
import dev.abarmin.aml.registration.domain.User;
import dev.abarmin.aml.registration.repository.AccountRepository;
import dev.abarmin.aml.registration.repository.ProfileRepository;
import dev.abarmin.aml.registration.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionTemplate;

import java.time.Instant;

import static com.google.common.base.Preconditions.checkArgument;

@Service
@RequiredArgsConstructor
public class RegistrationService {
  private final UserRepository userRepository;
  private final AccountRepository accountRepository;
  private final ProfileRepository profileRepository;
  private final PageRepository pageRepository;
  private final TransactionTemplate transactionTemplate;
  private final PasswordEncoder passwordEncoder;
  private final MailService mailService;
  private final MailTemplateService templateService;

  public User register(RegistrationForm form, AccountType type) {
    return transactionTemplate.execute(status -> registerInTx(form, type));
  }

  private User registerInTx(RegistrationForm form, AccountType type) {
    final User user = userRepository.findByEmail(form.getEmail())
      .orElseGet(() -> createUser(form));

    accountRepository.findByUserIdAndType(user.id(), type)
      .ifPresent(account -> {
        throw new IllegalArgumentException("Account already exists");
      });

    final Account account = createAccount(form, type, user);
    checkArgument(account != null, "Account wasn't created");

    final Profile profile = createProfile(form, user);
    final Page homePage = createHomePage(profile);

    checkArgument(homePage != null, "Home page wasn't created");
    checkArgument(homePage.isHome(), "Home page wasn't marked as home");

    final MailSendResult sendResult = sendWelcomeEmail(user);
    checkArgument(sendResult.isOk(), "Welcome email wasn't sent");

    return user;
  }

  private MailSendResult sendWelcomeEmail(User user) {
    final MailTemplate<User> template = templateService.registrationDone();
    return mailService.send(template, user);
  }

  private Page createHomePage(Profile profile) {
    return pageRepository.save(new Page(
      null,
      profile.id(),
      "Home",
      true,
      false,
      Instant.now(),
      null
    ));
  }

  private Profile createProfile(RegistrationForm form, User user) {
    return profileRepository.save(new Profile(
      null,
      user.id(),
      form.getLink(),
      Instant.now()
    ));
  }

  private Account createAccount(RegistrationForm form, AccountType type, User user) {
    final Account account = new Account(
      null,
      user.id(),
      type,
      passwordEncoder.encode(form.getPassword()),
      true,
      Instant.now()
    );
    return accountRepository.save(account);
  }

  private User createUser(RegistrationForm form) {
    return userRepository.save(new User(
      null,
      form.getEmail(),
      form.getName(),
      Instant.now()
    ));
  }
}
