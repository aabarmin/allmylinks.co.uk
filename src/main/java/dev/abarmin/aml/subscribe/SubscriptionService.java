package dev.abarmin.aml.subscribe;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SubscriptionService {
  private final SubscriptionRepository repository;

  public boolean subscribe(String email, SubscriptionSource source) {
    final Optional<Subscription> existing = repository.findByEmailAndSource(email, source);
    if (existing.isPresent()) {
      return false;
    }
    repository.save(new Subscription(email, source));

    return true;
  }
}
