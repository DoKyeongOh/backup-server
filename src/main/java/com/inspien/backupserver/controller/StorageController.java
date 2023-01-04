package com.inspien.backupserver.controller;

import com.inspien.backupserver.dto.CustomFileDTO;
import com.inspien.backupserver.dto.StorageCreationReqDTO;
import com.inspien.backupserver.dto.StorageModReqDTO;
import com.inspien.backupserver.service.CustomFileService;
import com.inspien.backupserver.service.StorageService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping("/storage")
public class StorageController {

    StorageService storageService;
    CustomFileService customFileService;

    @PostMapping("")
    public ResponseEntity<Map<String, String>> postStorage(@RequestBody StorageCreationReqDTO dto) {
        storageService.createStorage(dto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("")
    public ResponseEntity<List<CustomFileDTO>> getStorage(@RequestParam String rootDirPath) {
        storageService.getStorage(rootDirPath);
        return ResponseEntity.ok(customFileService.getCustomFileDTOS(rootDirPath));
    }

    @PutMapping("")
    public ResponseEntity<Object> putStorage(@RequestBody StorageModReqDTO dto) {
        storageService.updateStorage(dto);
        return ResponseEntity.ok().build();
    }

}
