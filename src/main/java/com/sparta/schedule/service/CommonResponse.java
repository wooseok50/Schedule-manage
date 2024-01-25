package com.sparta.schedule.service;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL) // Null 값인 필드 제외
public class CommonResponse<T> {
    private Integer statusCode;
    private String msg;
    private T data;
}
