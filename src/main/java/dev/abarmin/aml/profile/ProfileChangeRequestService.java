package dev.abarmin.aml.profile;

import dev.abarmin.aml.profile.domain.ChangeEmailRequest;
import dev.abarmin.aml.profile.domain.ChangeLinkRequest;
import dev.abarmin.aml.profile.domain.ProfileChangeRequest;
import dev.abarmin.aml.profile.domain.ProfileChangeStatus;
import dev.abarmin.aml.registration.domain.Account;
import dev.abarmin.aml.registration.domain.Profile;
import dev.abarmin.aml.registration.domain.User;
import dev.abarmin.aml.registration.repository.AccountRepository;
import dev.abarmin.aml.registration.repository.ProfileRepository;
import dev.abarmin.aml.registration.repository.UserRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.google.common.base.Preconditions.checkArgument;

@Service
@RequiredArgsConstructor
public class ProfileChangeRequestService {

  private final ProfileChangeRepository repository;
  private final AccountRepository accountRepository;
  private final ProfileRepository profileRepository;
  private final UserRepository userRepository;

  public ProfileChangeRequest process(final @NonNull ProfileChangeRequest request) {
    switch (request.getChangeType()) {
      case PROFILE_DEACTIVATE -> deactivate(request);
      case CHANGE_LINK -> changeLink(request);
      case CHANGE_EMAIL -> changeEmail(request);
      default -> throw new UnsupportedOperationException("Unsupported payload type " + request.getChangeType());
    }

    request.setChangeStatus(ProfileChangeStatus.PROCESSED);
    repository.save(request);

    return request;
  }

  private void deactivate(ProfileChangeRequest request) {
    final List<Account> accounts = accountRepository.findAllByUserId(request.getUserId());
    for (Account account : accounts) {
      account.setActive(false);
      accountRepository.save(account);
    }
  }

  private void changeLink(ProfileChangeRequest request) {
    checkArgument(request.getChangePayload() instanceof ChangeLinkRequest);
    final ChangeLinkRequest payload = (ChangeLinkRequest) request.getChangePayload();

    final Profile profile = profileRepository.findById(request.getProfileId()).orElseThrow();
    final Profile updatedProfile = profile.withLink(payload.getNewLink());

    profileRepository.save(updatedProfile);
  }

  private void changeEmail(ProfileChangeRequest request) {
    checkArgument(request.getChangePayload() instanceof ChangeEmailRequest);
    final ChangeEmailRequest payload = (ChangeEmailRequest) request.getChangePayload();

    final User user = userRepository.findById(request.getUserId()).orElseThrow();
    final User updatedUser = user.withEmail(payload.getNewEmail());

    userRepository.save(updatedUser);
  }
}
