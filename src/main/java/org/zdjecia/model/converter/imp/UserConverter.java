package org.zdjecia.model.converter.imp;

import org.springframework.stereotype.Component;
import org.zdjecia.model.converter.Converter;
import org.zdjecia.model.dto.UserRegisterDto;
import org.zdjecia.model.entities.User;

@Component(value = "userConverter")
public class UserConverter implements Converter<UserRegisterDto, User> {
    @Override
    public User convert(UserRegisterDto from) {
        return new User(
                from.getUserName().toLowerCase(),
                from.getUserPassword(),
                from.getEmail());
    }
}
