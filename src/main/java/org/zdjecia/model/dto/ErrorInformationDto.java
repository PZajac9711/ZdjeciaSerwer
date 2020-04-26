package org.zdjecia.model.dto;

//Klasa ktora bedzie opisywac errory przez ktore nie udalo sie wykonac żadania

public class ErrorInformationDto {
    private final String errorMessage;

    public ErrorInformationDto(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
