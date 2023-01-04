package com.inspien.backupserver.repository;

import com.inspien.backupserver.entity.CustomFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomFileRepository extends JpaRepository<CustomFile, Long> {
    List<CustomFile> findByRootPath(String rootPath);

    void deleteByFilename(String filename);
}
