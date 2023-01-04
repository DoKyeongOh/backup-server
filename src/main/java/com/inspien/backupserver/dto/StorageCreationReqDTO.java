package com.inspien.backupserver.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class StorageCreationReqDTO {
    List<CustomFileDTO> files;
    String rootDirPath;
}
