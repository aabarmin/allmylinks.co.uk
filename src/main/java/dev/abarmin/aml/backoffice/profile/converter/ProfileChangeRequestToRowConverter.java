package dev.abarmin.aml.backoffice.profile.converter;

import dev.abarmin.aml.backoffice.profile.model.ProfileChangeRequestRow;
import dev.abarmin.aml.backoffice.users.ProfileHelper;
import dev.abarmin.aml.profile.domain.ProfileChangeRequest;
import dev.abarmin.aml.registration.domain.Profile;
import dev.abarmin.aml.registration.domain.User;
import dev.abarmin.aml.registration.repository.ProfileRepository;
import dev.abarmin.aml.registration.repository.UserRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProfileChangeRequestToRowConverter {

  private final ProfileHelper profileHelper;
  private final UserRepository userRepository;
  private final ProfileRepository profileRepository;

  public ProfileChangeRequestRow convert(final @NonNull ProfileChangeRequest request) {
    final User user = userRepository.findById(request.getUserId()).orElseThrow();
    final Profile profile = profileRepository.findById(request.getProfileId()).orElseThrow();

    return ProfileChangeRequestRow.builder()
      .id(request.getId())
      .userEmail(user.email())
      .profileLink(profileHelper.getProfileLink(user))
      .profileName(profile.link())
      .requestStatus(request.getChangeStatus().name())
      .requestType(request.getChangeType().name())
      .build();
  }

}
