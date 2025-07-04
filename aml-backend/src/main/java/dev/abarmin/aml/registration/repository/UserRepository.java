package dev.abarmin.aml.registration.repository;

import dev.abarmin.aml.registration.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long>, PagingAndSortingRepository<User, Long> {
  Optional<User> findByEmail(String email);
}
