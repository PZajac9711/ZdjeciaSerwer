package org.zdjecia.services.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.password.PasswordEncoder;
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
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImp(UserRepository userRepository,
                          PasswordEncoder passwordEncoder,
                          @Qualifier("userConverter") Converter<UserDto, User> converter,
                          @Qualifier("jwtGenerate") JwtGenerate jwtGenerate){
        this.converter = converter;
        this.userRepository = userRepository;
        this.jwtGenerate = jwtGenerate;
        this.passwordEncoder = passwordEncoder;
    }
    @Override
    public Boolean createUser(UserDto userDto) {
        if(userRepository.findByUserName(userDto.getUserName()) == null){
            User user = converter.convert(userDto);
            user.setUserPassword(passwordEncoder.encode(user.getUserPassword()));
            userRepository.save(user);
            return true;
        }
        return false;
    }

    @Override
    public JwtTokenDto checkIfUserExistAndDataIsValid(UserDto userDto) {
        User user = userRepository.findByUserName(userDto.getUserName());
        return user == null  ? null : checkDataAndGenerateToken(user,userDto);
    }
    private JwtTokenDto checkDataAndGenerateToken(User user,UserDto userDto){
        return passwordEncoder.matches(userDto.getUserPassword(),user.getUserPassword()) ? new JwtTokenDto(jwtGenerate.generateToken(userDto.getUserName())) : null;
    }
}
