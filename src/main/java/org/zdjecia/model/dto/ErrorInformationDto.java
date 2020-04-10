package org.zdjecia.model.dto;

import lombok.Getter;

@Getter
public class ErrorInformationDto {
    private final String errorMessage;

    public ErrorInformationDto(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
