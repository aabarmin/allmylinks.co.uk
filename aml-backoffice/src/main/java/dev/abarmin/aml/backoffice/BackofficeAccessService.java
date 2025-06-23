package dev.abarmin.aml.backoffice;

import dev.abarmin.aml.registration.domain.Profile;
import dev.abarmin.aml.registration.domain.User;
import dev.abarmin.aml.registration.domain.UserRole;
import dev.abarmin.aml.registration.domain.UserRoles;
import dev.abarmin.aml.registration.repository.UserRepository;
import dev.abarmin.aml.registration.repository.UserRoleRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class BackofficeAccessService {
  private final UserRoleRepository roleRepository;
  private final UserRepository userRepository;

  public boolean grantBackofficeAccess(@NonNull Profile profile) {
    return userRepository.findById(profile.userId())
      .map(this::grantBackofficeAccess)
      .orElseThrow(() -> new IllegalArgumentException("User not found"));
  }

  public boolean grantBackofficeAccess(@NonNull User user) {
    final Optional<UserRole> roleOptional =
      roleRepository.findByUserIdAndRole(user.id(), UserRoles.BACKOFFICE);
    if (roleOptional.isPresent()) {
      log.debug("Backoffice admin role already exists");
      return false;
    }
    final UserRole adminRole = UserRole.backofficeAccess(user.id());
    roleRepository.save(adminRole);

    return true;
  }
}
