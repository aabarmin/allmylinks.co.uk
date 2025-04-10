package dev.abarmin.aml.registration;

import com.google.common.collect.Lists;
import dev.abarmin.aml.dashboard.block.avatar.AvatarBlockProps;
import dev.abarmin.aml.dashboard.block.button.LinkButtonBlockProps;
import dev.abarmin.aml.dashboard.block.header.HeaderBlockProps;
import dev.abarmin.aml.dashboard.block.header.HeaderLevel;
import dev.abarmin.aml.dashboard.block.header.TextAlignment;
import dev.abarmin.aml.dashboard.domain.BasicPageProps;
import dev.abarmin.aml.dashboard.domain.Block;
import dev.abarmin.aml.dashboard.domain.BlockType;
import dev.abarmin.aml.dashboard.domain.Page;
import dev.abarmin.aml.dashboard.repository.BlockRepository;
import dev.abarmin.aml.dashboard.repository.PageRepository;
import dev.abarmin.aml.mail.task.SendEmailRequest;
import dev.abarmin.aml.registration.domain.Account;
import dev.abarmin.aml.registration.domain.AccountType;
import dev.abarmin.aml.registration.domain.Profile;
import dev.abarmin.aml.registration.domain.User;
import dev.abarmin.aml.registration.repository.AccountRepository;
import dev.abarmin.aml.registration.repository.ProfileRepository;
import dev.abarmin.aml.registration.repository.UserRepository;
import dev.abarmin.aml.task.AddTaskResponse;
import dev.abarmin.aml.task.TaskService;
import dev.abarmin.aml.telegram.task.SendTelegramMessageRequest;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionTemplate;

import java.time.Instant;
import java.util.List;

import static com.google.common.base.Preconditions.checkArgument;

@Service
@RequiredArgsConstructor
public class RegistrationService {
  private final UserRepository userRepository;
  private final AccountRepository accountRepository;
  private final ProfileRepository profileRepository;
  private final PageRepository pageRepository;
  private final BlockRepository blockRepository;
  private final TransactionTemplate transactionTemplate;
  private final PasswordEncoder passwordEncoder;
  private final TaskService taskService;

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

    final List<Block> pageBlocks = createHomePageBlocks(user, homePage);

    checkArgument(!pageBlocks.isEmpty(), "Page blocks should have been added");

    final boolean sendResult = sendWelcomeEmail(user);
    checkArgument(sendResult, "Welcome email wasn't sent");

    final boolean adminNotification = sendWelcomeEmailToAdmin(user);
    checkArgument(adminNotification, "Admin notification wasn't sent");

    return user;
  }

  private boolean sendWelcomeEmail(User user) {
    final SendEmailRequest request = SendEmailRequest.builder()
      .template("registrationDone")
      .objectId(user.id())
      .build();

    final AddTaskResponse response = taskService.addTask(SendEmailRequest.TASK_TYPE, request);

    return response.getResult() == AddTaskResponse.Result.SUCCESS;
  }

  private boolean sendWelcomeEmailToAdmin(User user) {
    final SendTelegramMessageRequest request = SendTelegramMessageRequest.builder()
      .template("registrationDoneAdmin")
      .objectId(user.id())
      .build();

    final AddTaskResponse response = taskService.addTask(SendTelegramMessageRequest.TASK_TYPE, request);

    return response.getResult() == AddTaskResponse.Result.SUCCESS;
  }

  private List<Block> createHomePageBlocks(User user, Page page) {
    final List<Block> blocks = Lists.newArrayList();
    blocks.add(blockRepository.save(new Block(
      page.id(),
      BlockType.AVATAR_BLOCK,
      0,
      AvatarBlockProps.builder()
        .avatarId(AvatarBlockProps.DEFAULT_AVATAR)
        .build()
    )));
    blocks.add(blockRepository.save(new Block(
      page.id(),
      BlockType.HEADER_BLOCK,
      1,
      HeaderBlockProps.builder()
        .text(user.userName())
        .alignment(TextAlignment.CENTER)
        .level(HeaderLevel.H1)
        .build()
    )));
    blocks.add(blockRepository.save(new Block(
      page.id(),
      BlockType.BUTTON_BLOCK,
      2,
      LinkButtonBlockProps.builder()
        .text("Send me an email")
        .link("mailto:" + user.email())
        .build()
    )));
    return blocks;
  }

  private Page createHomePage(Profile profile) {
    return pageRepository.save(new Page(
      null,
      profile.id(),
      "Home",
      true,
      false,
      new BasicPageProps(),
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
    final Account account = switch (type) {
      case USERNAME_PASSWORD: yield createUsernameAndPasswordAccount(form, user);
      case OIDC: yield createOidcAccount(form, user);
    };

    return accountRepository.save(account);
  }

  private Account createUsernameAndPasswordAccount(RegistrationForm form, User user) {
    final Account account = new Account(
      null,
      user.id(),
      AccountType.USERNAME_PASSWORD,
      passwordEncoder.encode(form.getPassword()),
      true,
      Instant.now()
    );
    return account;
  }

  private Account createOidcAccount(RegistrationForm form, User user) {
    final Account account = new Account(
      null,
      user.id(),
      AccountType.OIDC,
      "",
      true,
      Instant.now()
    );
    return account;
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
