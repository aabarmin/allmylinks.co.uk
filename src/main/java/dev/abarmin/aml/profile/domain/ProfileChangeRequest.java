package dev.abarmin.aml.profile.domain;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.Instant;

@Data
@Builder
@Table("user_profile_requests")
public class ProfileChangeRequest {
  @Id
  private Long id;
  @Column("user_id")
  private long userId;
  @Column("profile_id")
  private long profileId;
  @Column("change_type")
  private ProfileChangeType changeType;
  @Builder.Default
  @Column("change_status")
  private ProfileChangeStatus changeStatus = ProfileChangeStatus.CREATED;
  @Column("change_payload")
  private ProfileChangePayload changePayload;
  @Builder.Default
  @Column("created_at")
  private Instant createdAt = Instant.now();
  @Column("updated_at")
  private Instant updatedAt;

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
