package org.zdjecia.services;

import org.zdjecia.model.dto.JwtTokenDto;
import org.zdjecia.model.dto.UserDto;
import org.zdjecia.model.dto.UserRegisterDto;

import java.util.Optional;

public interface UserService {
    Optional<JwtTokenDto> checkIfUserExistAndDataIsValid(UserDto userDto);
    Boolean createUser(UserRegisterDto userDto);
}
