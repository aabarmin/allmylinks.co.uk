package dev.abarmin.aml.profile;

import dev.abarmin.aml.profile.domain.ProfileChangeRequest;
import dev.abarmin.aml.profile.domain.ProfileChangeStatus;
import dev.abarmin.aml.profile.domain.ProfileChangeType;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ProfileChangeRepository extends CrudRepository<ProfileChangeRequest, Long> {
  Optional<ProfileChangeRequest> findByProfileIdAndChangeTypeAndChangeStatus(
    long profileId, ProfileChangeType changeType, ProfileChangeStatus changeStatus
  );
}
