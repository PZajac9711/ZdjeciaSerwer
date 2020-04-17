package org.zdjecia.controllers;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.zdjecia.model.dto.ImageDto;
import org.zdjecia.services.ImageService;

@RestController
public class PrivateController {
    private final ImageService imageService;

    public PrivateController(@Qualifier("imageService") ImageService imageService) {
        this.imageService = imageService;
    }

    @GetMapping(value = "/random")
    public ImageDto getRandomImage(){
        return imageService.getRandomImage();
    }

    @GetMapping(value = "/insert")
    public void insert(){
        //
    }
    @GetMapping(value = "/getByTitle")
    public ImageDto getImageByTitle(@RequestParam String title){
        return imageService.findImageByTitle(title);
    }
}
