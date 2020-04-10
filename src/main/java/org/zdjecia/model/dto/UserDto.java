package org.zdjecia.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserDto {
    private final String userName;
    private final String userPassword;
}
