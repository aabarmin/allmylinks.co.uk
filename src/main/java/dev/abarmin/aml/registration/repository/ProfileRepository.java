package dev.abarmin.aml.registration.repository;

import dev.abarmin.aml.registration.domain.Profile;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ProfileRepository extends CrudRepository<Profile, Long> {
  Optional<Profile> findByLink(String link);
  Optional<Profile> findByUserId(long userId);
}
