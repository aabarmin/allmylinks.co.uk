package dev.abarmin.aml.file;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface FileRepository extends CrudRepository<FileEntity, Long> {
  Optional<FileEntity> findByPathAndStorage(String path, Storage storage);
}
