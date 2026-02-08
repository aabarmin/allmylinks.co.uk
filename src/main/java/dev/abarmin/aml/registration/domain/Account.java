package dev.abarmin.aml.registration.domain;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.Instant;

@Data
@Builder
@Table("user_accounts")
public class Account {
  @Id
  private Long id;
  @Column("user_id")
  private long userId;
  @Column("account_type")
  private AccountType type;
  @Column("account_password")
  private String password;
  @Column("account_is_active")
  private boolean isActive;
  @Builder.Default
  @Column("created_at")
  private Instant createdAt = Instant.now();
}
