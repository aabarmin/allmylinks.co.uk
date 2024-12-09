package dev.abarmin.aml.registration.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.Instant;

@Table("user_accounts")
public record Account(
  @Id Long id,
  @Column("user_id") long userId,
  @Column("account_type") AccountType type,
  @Column("account_password") String password,
  @Column("account_is_active") boolean isActive,
  @Column("created_at") Instant createdAt
) {
}
