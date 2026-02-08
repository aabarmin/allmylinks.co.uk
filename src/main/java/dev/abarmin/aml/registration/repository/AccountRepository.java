package dev.abarmin.aml.registration.repository;

import dev.abarmin.aml.registration.domain.Account;
import dev.abarmin.aml.registration.domain.AccountType;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface AccountRepository extends CrudRepository<Account, Long> {
  Optional<Account> findByUserIdAndType(long userId, AccountType type);

  List<Account> findAllByUserId(long userId);
}
