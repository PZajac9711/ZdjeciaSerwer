package org.zdjecia.model.dto;


public class JwtTokenDto {
    private final String token;

    public JwtTokenDto(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }
}
