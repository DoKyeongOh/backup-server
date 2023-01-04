package com.inspien.backupserver.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    // 파라미터 오류
    INPUT_FILES_IS_NOT_USABLE("입력받은 파일이 비어있거나 없습니다.", HttpStatus.NOT_FOUND),

    // 저장소 오류
    STORAGE_IS_ALREADY_EXIST("저장소가 이미 존재하기 때문에 새로 만들 수 없습니다.", HttpStatus.NO_CONTENT),
    STORAGE_IS_NOT_EXIST("저장소가 존재하지 않습니다.", HttpStatus.NOT_ACCEPTABLE);

    private String detail;
    private HttpStatus status;
}
