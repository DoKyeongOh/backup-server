package com.inspien.backupserver.service;

import com.inspien.backupserver.dto.CustomFileDTO;
import com.inspien.backupserver.entity.CustomFile;
import com.inspien.backupserver.repository.CustomFileRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CustomFileService {
    CustomFileRepository customFileRepository;

    public List<CustomFileDTO> getCustomFileDTOS(String rootDirPath) {
        List<CustomFile> customFiles = customFileRepository.findByRootPath(rootDirPath);
        return convertCustomFileDTO(customFiles);
    }

    public List<CustomFileDTO> convertCustomFileDTO(List<CustomFile> customFiles) {
        List<CustomFileDTO> customFileDTOS = new ArrayList<>();
        for (CustomFile file : customFiles) {
            customFileDTOS.add(new CustomFileDTO(file));
        }
        return customFileDTOS;
    }

    @Transactional
    public void deleteAllByFilenames(List<CustomFile> deletedFiles) {
        for (CustomFile file : deletedFiles) {
            file.removeStorage();
            customFileRepository.deleteByFilename(file.getFilename());
        }
    }
}
