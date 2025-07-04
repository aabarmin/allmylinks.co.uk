package dev.abarmin.aml.subscribe;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.Instant;

@Table("subscriptions")
public record Subscription(@Id Long id, String email, SubscriptionSource source, Instant createdAt) {
  public Subscription(String email, SubscriptionSource source) {
    this(null, email, source, Instant.now());
  }
}
