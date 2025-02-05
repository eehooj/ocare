package com.example.ocare.global.exception.enumerate;

import com.example.ocare.global.common.dto.ErrorResponse;
import com.example.ocare.global.exception.ExceptionTypes;
import org.springframework.http.HttpStatus;

public enum FileExceptionType implements ExceptionTypes {

    FILE_READ_FAILED() {
        @Override
        public ErrorResponse getErrorResponse() {
            return ErrorResponse.builder()
                    .httpStatus(HttpStatus.INTERNAL_SERVER_ERROR)
                    .exceptionType(this)
                    .message("파일 처리에 실패했습니다.")
                    .build();
        }
    },

    EXCEL_EXPORT_FAILED() {
        @Override
        public ErrorResponse getErrorResponse() {
            return ErrorResponse.builder()
                    .httpStatus(HttpStatus.INTERNAL_SERVER_ERROR)
                    .exceptionType(this)
                    .message("엑셀 처리에 실패했습니다.")
                    .build();
        }
    }
}
