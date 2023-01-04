package com.inspien.backupserver.repository;

import com.inspien.backupserver.entity.Storage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StorageRepository extends JpaRepository<Storage, Long> {
    Optional<Storage> findByRootPath(String rootPath);
}
