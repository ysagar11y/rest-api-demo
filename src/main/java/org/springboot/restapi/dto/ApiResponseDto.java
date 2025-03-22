package org.springboot.restapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class ApiResponseDto<T> {
    private String status;
    private T data;

    public ApiResponseDto(String status, T data) {
        this.status = status;
        this.data = data;
    }

}
