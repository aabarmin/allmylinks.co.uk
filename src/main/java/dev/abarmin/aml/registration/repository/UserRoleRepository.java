package dev.abarmin.aml.registration.repository;

import dev.abarmin.aml.registration.domain.UserRole;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRoleRepository extends CrudRepository<UserRole, Long> {
  Optional<UserRole> findByUserIdAndRole(long userId, String role);
}
