package org.zdjecia.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PrivateController {
    @GetMapping(value = "/test")
    public String x(){
        return "x";
    }
}
