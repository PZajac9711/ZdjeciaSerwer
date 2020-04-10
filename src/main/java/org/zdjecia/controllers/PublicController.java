package org.zdjecia.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zdjecia.services.UserService;

@RestController
public class PublicController {
    private UserService userService;

    @Autowired
    public PublicController(UserService userService) {
        this.userService = userService;
    }


}
