package com.inspien.backupserver.entity;

import com.inspien.backupserver.dto.StorageModReqDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Entity
@Builder
@Getter
@AllArgsConstructor
public class Storage {

    @Id @GeneratedValue
    Long storageEntryNo;

    @Column
    String rootPath;

    @OneToMany(mappedBy = "storage", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    List<CustomFile> customFiles;

    public Storage() {

    }
    public void update(StorageModReqDTO dto) {
        Map<String, CustomFile> deletedMap = dto.getDeletedFileMap();
        Map<String, CustomFile> modifiedMap = dto.getModifiedFileMap();
        for (Iterator<CustomFile> it = customFiles.iterator(); it.hasNext(); ) {
            CustomFile customFile = it.next();
            String filename = customFile.getFilename();
            if (deletedMap.containsKey(filename)) {
                it.remove();
                continue;
            }
            if (modifiedMap.containsKey(filename)) {
                customFile.convertValues(modifiedMap.get(filename));
            }
        }
        customFiles.addAll(dto.getAddedFiles());
    }
}
