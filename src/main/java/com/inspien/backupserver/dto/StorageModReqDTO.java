package com.inspien.backupserver.dto;

import com.inspien.backupserver.entity.CustomFile;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
public class StorageModReqDTO {
    String rootDirPath;
    List<CustomFile> addedFiles;
    List<CustomFile> deletedFiles;
    List<CustomFile> modifiedFiles;

    public Map<String, CustomFile> getDeletedFileMap() {
        Map<String, CustomFile> deletedMap = new HashMap();
        for (CustomFile cf : deletedFiles) {
            deletedMap.put(cf.getFilename(), cf);
        }
        return deletedMap;
    }

    public Map<String, CustomFile> getModifiedFileMap() {
        Map<String, CustomFile> modifiedMap = new HashMap();
        for (CustomFile cf : modifiedFiles) {
            modifiedMap.put(cf.getFilename(), cf);
        }
        return modifiedMap;
    }
}
