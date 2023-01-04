package com.inspien.backupserver.dto;

import com.inspien.backupserver.entity.CustomFile;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class CustomFileDTO {

    String filename;

    String rootPath;

    Long lastModified;

    byte[] fileData;

    public CustomFileDTO() {
    }

    public CustomFileDTO(CustomFile customFile) {
        this.filename = customFile.getFilename();
        this.rootPath = customFile.getRootPath();
        this.lastModified = customFile.getLastModified();
        this.fileData = customFile.getFileData();
    }

}
