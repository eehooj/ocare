package com.example.ocare.global.common.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResultResponse<T> {

    private HttpStatus status;

    private T body;

    public ResultResponse(HttpStatus status) {
        this.status = status;
    }

    public ResultResponse(HttpStatus status, T body) {
        this.status = status;
        this.body = body;
    }
}
