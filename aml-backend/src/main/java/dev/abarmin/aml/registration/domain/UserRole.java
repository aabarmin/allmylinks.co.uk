package dev.abarmin.aml.registration.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.Instant;

@Table("user_roles")
public record UserRole(
  @Id @Column("id") Long id,
  @Column("user_id") long userId,
  @Column("role") String role,
  @Column("created_at") Instant createdAt,
  @Column("updated_at") Instant updatedAt
) {

  public static UserRole backofficeAccess(long userId) {
    return new UserRole(null, userId, UserRoles.BACKOFFICE, Instant.now(), null);
  }
}
