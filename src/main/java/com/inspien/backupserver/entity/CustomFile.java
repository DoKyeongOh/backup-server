package com.inspien.backupserver.entity;

import com.inspien.backupserver.dto.CustomFileDTO;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;


@Getter
@Entity
public class CustomFile {

    @Id
    @GeneratedValue
    Long fileEntryNo;

    @Column
    String filename;

    @Column
    Long lastModified;

    @Column
    String rootPath;

    @Column
    byte[] fileData;

    @ManyToOne
    @JoinColumn
    Storage storage;

    public CustomFile() {

    }

    @Builder
    public CustomFile(Long fileEntryNo, String filename, Long lastModified, String rootPath, byte[] fileData, Storage storage) {
        this.fileEntryNo = fileEntryNo;
        this.filename = filename;
        this.lastModified = lastModified;
        this.rootPath = rootPath;
        this.fileData = fileData;
        this.storage = storage;
    }

    public CustomFile(CustomFileDTO dto, Storage storage) {
        this.filename = dto.getFilename();
        this.lastModified = dto.getLastModified();
        this.rootPath = dto.getRootPath();
        this.fileData = dto.getFileData();
        this.storage = storage;
    }

    public void convertValues(CustomFile customFile) {
        this.lastModified = customFile.getLastModified();
        this.fileData = customFile.getFileData();
    }

    public void removeStorage() {
        this.storage = null;
    }
}
