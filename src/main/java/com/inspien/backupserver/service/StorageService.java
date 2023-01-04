package com.inspien.backupserver.service;

import com.inspien.backupserver.dto.CustomFileDTO;
import com.inspien.backupserver.dto.StorageCreationReqDTO;
import com.inspien.backupserver.dto.StorageModReqDTO;
import com.inspien.backupserver.entity.CustomFile;
import com.inspien.backupserver.entity.Storage;
import com.inspien.backupserver.exception.CustomException;
import com.inspien.backupserver.exception.ErrorCode;
import com.inspien.backupserver.repository.StorageRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class StorageService {

    StorageRepository storageRepository;
    CustomFileService customFileService;

    public void createStorage(StorageCreationReqDTO dto) {
        String rootPath = dto.getRootDirPath();
        if (isExistStorage(rootPath)) {
            throw new CustomException(ErrorCode.STORAGE_IS_ALREADY_EXIST);
        }

        List<CustomFile> customFiles = new ArrayList<>();
        Storage storage = Storage.builder()
                .rootPath(rootPath)
                .customFiles(customFiles)
                .build();

        addAllFileToStorage(storage, dto.getFiles());
        storageRepository.save(storage);
    }

    public Storage getStorage(String rootPath) {
        return storageRepository.findByRootPath(rootPath)
                .orElseThrow(() -> new CustomException(ErrorCode.STORAGE_IS_NOT_EXIST));
    }

    public boolean isExistStorage(String rootPath) {
        return storageRepository.findByRootPath(rootPath)
                .isPresent();
    }

    private void addAllFileToStorage(Storage storage, List<CustomFileDTO> customFileDTOS) {
        customFileDTOS.forEach(dto -> storage.getCustomFiles().add(new CustomFile(dto, storage)));
    }

    @Transactional
    public void updateStorage(StorageModReqDTO dto) {
        Storage storage = getStorage(dto.getRootDirPath());
        storage.update(dto);
        customFileService.deleteAllByFilenames(dto.getDeletedFiles());
    }
}
