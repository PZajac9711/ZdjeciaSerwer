package org.zdjecia.services.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.zdjecia.model.converter.Converter;
import org.zdjecia.model.dto.JwtTokenDto;
import org.zdjecia.model.dto.UserDto;
import org.zdjecia.model.entities.User;
import org.zdjecia.model.repository.UserRepository;
import org.zdjecia.model.security.JwtGenerate;
import org.zdjecia.services.UserService;

@Service(value = "userServiceImp")
public class UserServiceImp implements UserService {
    private UserRepository userRepository;
    private Converter<UserDto, User> converter;
    private JwtGenerate jwtGenerate;
    @Autowired
    public UserServiceImp(UserRepository userRepository,
                          @Qualifier("userConverter") Converter<UserDto, User> converter,
                          @Qualifier("jwtGenerate") JwtGenerate jwtGenerate){
        this.converter = converter;
        this.userRepository = userRepository;
        this.jwtGenerate = jwtGenerate;
    }


    @Override
    public JwtTokenDto checkIfUserExist(UserDto userDto) {
        return userRepository.findByUserNameAndUserPassword(userDto.getUserName(),userDto.getUserPassword()) == null
                ? null : new JwtTokenDto(jwtGenerate.generateToken(userDto.getUserName()));
    }
}
