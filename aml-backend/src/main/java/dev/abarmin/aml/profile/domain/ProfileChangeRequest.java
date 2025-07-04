package dev.abarmin.aml.profile.domain;

import dev.abarmin.aml.registration.domain.Profile;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.Instant;

@Table("user_profile_requests")
public record ProfileChangeRequest(
  @Id Long id,
  @Column("user_id") long userId,
  @Column("profile_id") long profileId,
  @Column("change_type") ProfileChangeType changeType,
  @Column("change_status") ProfileChangeStatus changeStatus,
  @Column("change_payload") ProfileChangePayload changePayload,
  @Column("created_at") Instant createdAt,
  @Column("updated_at") Instant updatedAt
) {

  public ProfileChangeRequest(Profile profile, ProfileChangeType type, ProfileChangePayload payload) {
    this(
      null,
      profile.userId(),
      profile.id(),
      type,
      ProfileChangeStatus.CREATED,
      payload,
      Instant.now(),
      null
    );
  }

  public ProfileChangeRequest cancel() {
    return new ProfileChangeRequest(
      id,
      userId,
      profileId,
      changeType,
      ProfileChangeStatus.REJECTED,
      changePayload,
      createdAt,
      Instant.now()
    );
  }

}
