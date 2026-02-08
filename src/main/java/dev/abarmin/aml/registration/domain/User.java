package dev.abarmin.aml.registration.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.Instant;

@Table("users")
public record User(
  @Id Long id,
  @Column("user_email") String email,
  @Column("user_name") String userName,
  @Column("created_at") Instant createdAt
) {

  public User withEmail(String newEmail) {
    return new User(id, newEmail, userName, createdAt);
  }

}
