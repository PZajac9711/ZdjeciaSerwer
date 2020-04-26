package org.zdjecia.services;

import org.zdjecia.model.dto.JwtTokenDto;
import org.zdjecia.model.dto.UserDto;

public interface UserService {
    JwtTokenDto checkIfUserExistAndDataIsValid(UserDto userDto);
    Boolean createUser(UserDto userDto);
}
