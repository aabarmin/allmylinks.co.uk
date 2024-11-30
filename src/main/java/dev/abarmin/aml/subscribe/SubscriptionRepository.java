package dev.abarmin.aml.subscribe;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface SubscriptionRepository extends CrudRepository<Subscription, Long> {
  Optional<Subscription> findByEmailAndSource(String email, SubscriptionSource source);
}
