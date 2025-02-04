package com.example.ocare.global.exception.enumerate;

import com.example.ocare.global.common.dto.ErrorResponse;
import com.example.ocare.global.exception.ExceptionTypes;
import org.springframework.http.HttpStatus;

public enum DataExceptionType implements ExceptionTypes {

    DATA_NOT_FOUND() {
        @Override
        public ErrorResponse getErrorResponse() {
            return ErrorResponse.builder()
                    .httpStatus(HttpStatus.INTERNAL_SERVER_ERROR)
                    .exceptionType(this)
                    .message("데이터 처리에 실패했습니다.")
                    .build();
        }
    }
}
