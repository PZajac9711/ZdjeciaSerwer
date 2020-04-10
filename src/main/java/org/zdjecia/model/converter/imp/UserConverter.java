package org.zdjecia.model.converter.imp;

import org.springframework.stereotype.Component;
import org.zdjecia.model.converter.Converter;
import org.zdjecia.model.dto.UserDto;
import org.zdjecia.model.entities.User;

@Component(value = "userConverter")
public class UserConverter implements Converter<UserDto, User> {
    @Override
    public User convert(UserDto from) {
        return new User(
                from.getUserName(),
                from.getUserPassword()
        );
    }
}
