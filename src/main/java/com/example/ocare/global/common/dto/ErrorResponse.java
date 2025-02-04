package com.example.ocare.global.common.dto;

import com.example.ocare.global.exception.ExceptionTypes;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@Builder
@JsonInclude(Include.NON_NULL)
public class ErrorResponse {

    private HttpStatus httpStatus;

    private ExceptionTypes exceptionType;

    private String message;

}
