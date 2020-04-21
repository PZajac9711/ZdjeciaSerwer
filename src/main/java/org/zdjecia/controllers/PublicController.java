package org.zdjecia.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.zdjecia.model.dto.JwtTokenDto;
import org.zdjecia.model.dto.UserDto;
import org.zdjecia.services.UserService;

@RestController
public class PublicController {
    private UserService userService;

    @Autowired
    public PublicController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(value = "/login")
    public ResponseEntity<JwtTokenDto> loginMethod(@RequestBody UserDto userDto){
        JwtTokenDto jwtTokenDto = userService.checkIfUserExistAndDataIsValid(userDto);
        return jwtTokenDto == null ? new ResponseEntity<>(null, HttpStatus.BAD_REQUEST) : new ResponseEntity<>(jwtTokenDto, HttpStatus.OK);
    }
    @PostMapping(value = "/register")
    public ResponseEntity<Void> registerMethod(@RequestBody UserDto userDto){
        return userService.createUser(userDto) ? new ResponseEntity<>(HttpStatus.CREATED) : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

}
