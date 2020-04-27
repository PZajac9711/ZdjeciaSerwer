package org.zdjecia.services.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.zdjecia.model.converter.Converter;
import org.zdjecia.model.dto.JwtTokenDto;
import org.zdjecia.model.dto.UserDto;
import org.zdjecia.model.dto.UserRegisterDto;
import org.zdjecia.model.entities.User;
import org.zdjecia.model.expresions.RegularExpresions;
import org.zdjecia.model.repository.UserRepository;
import org.zdjecia.model.security.JwtGenerate;
import org.zdjecia.services.UserService;

import java.util.Optional;

@Service(value = "userServiceImp")
public class UserServiceImp implements UserService {
    private UserRepository userRepository;
    private Converter<UserRegisterDto, User> converter;
    private PasswordEncoder passwordEncoder;
    private JwtGenerate jwtGenerate;

    @Autowired
    public UserServiceImp(UserRepository userRepository,
                          PasswordEncoder passwordEncoder,
                          @Qualifier("jwtGenerate") JwtGenerate jwtGenerate,
                          @Qualifier("userConverter") Converter<UserRegisterDto, User> converter){
        this.converter = converter;
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.jwtGenerate = jwtGenerate;
    }
    @Override
    public Boolean createUser(UserRegisterDto userDto) {
        User user = converter.convert(userDto);
        if((userRepository.findByUserName(user.getUserName()).isEmpty())
                && RegularExpresions.validUserName(userDto.getUserName())
                && RegularExpresions.validUserPassword(userDto.getUserPassword())
                && RegularExpresions.validUserEmail(userDto.getEmail()))
        {
            user.setUserPassword(passwordEncoder.encode(user.getUserPassword()));
            userRepository.save(user);
            return true;
        }
        return false;
    }

    @Override
    public Optional<JwtTokenDto> checkIfUserExistAndDataIsValid(UserDto userDto) {
        Optional<User> user = userRepository.findByUserName(userDto.getUserName().toLowerCase());
        return user.isEmpty()  ? Optional.empty() : checkDataAndGenerateToken(user.get(),userDto);
    }
    private Optional<JwtTokenDto> checkDataAndGenerateToken(User user,UserDto userDto){
        if(passwordEncoder.matches(userDto.getUserPassword(),user.getUserPassword())){
            JwtTokenDto jwtTokenDto = new JwtTokenDto(jwtGenerate.generateToken(userDto.getUserName()));
            return Optional.of(jwtTokenDto);
        }
        return Optional.empty();
    }
}
