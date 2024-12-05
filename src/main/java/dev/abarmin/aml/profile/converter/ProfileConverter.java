package dev.abarmin.aml.profile.converter;

import dev.abarmin.aml.profile.ProfileChangeRepository;
import dev.abarmin.aml.profile.model.ProfileModel;
import dev.abarmin.aml.profile.domain.ProfileChangeStatus;
import dev.abarmin.aml.profile.domain.ProfileChangeType;
import dev.abarmin.aml.registration.domain.Profile;
import dev.abarmin.aml.registration.domain.User;
import dev.abarmin.aml.registration.repository.UserRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProfileConverter {
  private final ProfileChangeRepository repository;
  private final UserRepository userRepository;

  public ProfileModel convert(@NonNull Profile profile) {
    final User user = userRepository.findById(profile.userId())
      .orElseThrow(() -> new IllegalStateException("User not found"));

    return ProfileModel.builder()
      .link(profile.link())
      .currentEmail(user.email())
      .hasActiveDeactivationRequest(hasActiveRequest(profile.id(), ProfileChangeType.PROFILE_DEACTIVATE))
      .hasActiveEmailChangeRequest(hasActiveRequest(profile.id(), ProfileChangeType.CHANGE_EMAIL))
      .hasActiveLinkChangeRequest(hasActiveRequest(profile.id(), ProfileChangeType.CHANGE_LINK))
      .build();
  }

  private boolean hasActiveRequest(long profileId, ProfileChangeType type) {
    return repository.findByProfileIdAndChangeTypeAndChangeStatus(
      profileId, type, ProfileChangeStatus.CREATED
    ).isPresent();
  }
}
