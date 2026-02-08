package dev.abarmin.aml.profile;

import dev.abarmin.aml.profile.domain.ProfileChangeRequest;
import dev.abarmin.aml.profile.domain.ProfileChangeStatus;
import dev.abarmin.aml.profile.domain.ProfileChangeType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ProfileChangeRepository extends CrudRepository<ProfileChangeRequest, Long> {

  Optional<ProfileChangeRequest> findByProfileIdAndChangeTypeAndChangeStatus(
    long profileId, ProfileChangeType changeType, ProfileChangeStatus changeStatus
  );

  List<ProfileChangeRequest> findByProfileIdAndChangeType(
    long profileId, ProfileChangeType changeType
  );

  Page<ProfileChangeRequest> findAllByChangeStatus(ProfileChangeStatus status, Pageable pageable);

  long countAllByChangeStatus(ProfileChangeStatus status);
}
