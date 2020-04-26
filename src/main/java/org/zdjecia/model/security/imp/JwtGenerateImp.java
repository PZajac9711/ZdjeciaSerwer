package org.zdjecia.model.security.imp;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;
import org.zdjecia.model.security.JwtGenerate;
import org.zdjecia.model.security.configuration.JwtConfig;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;

@Component(value = "jwtGenerate")
public class JwtGenerateImp implements JwtGenerate {
    @Override
    public String generateToken(String userName) {
        long currentTime = System.currentTimeMillis();
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(JwtConfig.getSecret());
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

        return Jwts.builder()
                .setSubject("Login")
                .claim("roles","user")
                .claim("login",userName)
                .setIssuedAt(new Date(currentTime))
                .setExpiration(new Date(currentTime + JwtConfig.getExpirationTime()))
                .signWith(signatureAlgorithm, signingKey)
                .compact();
    }
}
