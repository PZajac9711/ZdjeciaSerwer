package org.zdjecia.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.zdjecia.model.dto.JwtTokenDto;
import org.zdjecia.model.dto.UserDto;
import org.zdjecia.model.dto.UserRegisterDto;
import org.zdjecia.services.UserService;

import java.util.Optional;

@RestController
public class PublicController {
    private final UserService userService;

    @Autowired
    public PublicController(UserService userService) {
        this.userService = userService;
    }

    @CrossOrigin
    @PostMapping(value = "/login")
    public ResponseEntity<JwtTokenDto> loginMethod(@RequestBody UserDto userDto){
        Optional<JwtTokenDto> jwtTokenDto = userService.checkIfUserExistAndDataIsValid(userDto);
        return jwtTokenDto.isEmpty() ? new ResponseEntity<>(null, HttpStatus.BAD_REQUEST) : new ResponseEntity<>(jwtTokenDto.get(), HttpStatus.OK);
    }
    @PostMapping(value = "/register")
    public ResponseEntity<Void> registerMethod(@RequestBody UserRegisterDto userDto){
        return userService.createUser(userDto) ? new ResponseEntity<>(HttpStatus.CREATED) : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

}
