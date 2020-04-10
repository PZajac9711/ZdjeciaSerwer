package org.zdjecia.model.security;

public interface JwtGenerate {
    String generateToken(String userName);
}
