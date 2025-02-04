package com.example.ocare.global.advice;

import com.example.ocare.global.common.dto.ErrorResponse;
import com.example.ocare.global.exception.CustomException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@RequiredArgsConstructor
public class ExceptionAdvice {

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ErrorResponse> handleCustomException(CustomException e) {
        ErrorResponse err = e.getExceptionTypes().getErrorResponse();

        return new ResponseEntity<>(err, err.getHttpStatus());
    }

}
