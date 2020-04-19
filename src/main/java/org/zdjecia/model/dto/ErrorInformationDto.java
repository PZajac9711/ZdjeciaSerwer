package org.zdjecia.model.dto;

import lombok.Getter;
//Klasa ktora bedzie opisywac errory przez ktore nie udalo sie wykonac żadania 
@Getter
public class ErrorInformationDto {
    private final String errorMessage;

    public ErrorInformationDto(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
