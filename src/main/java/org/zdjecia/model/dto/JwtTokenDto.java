package org.zdjecia.model.dto;

import lombok.Getter;

@Getter
public class JwtTokenDto {
    private final String token;

    public JwtTokenDto(String token) {
        this.token = token;
    }
}
