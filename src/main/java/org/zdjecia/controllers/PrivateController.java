package org.zdjecia.controllers;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.zdjecia.model.dto.ImageDto;
import org.zdjecia.model.dto.ScoreDto;
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
    @PostMapping(value = "/score") // Pytanie co zwracac ?? czy zwracac true/false i na tej podstawie wywolywac skrypt js ktore odrazu bedzie updatowal wynik na stornie ?
    public void userClickScore(@RequestBody ScoreDto scoreDto){
        imageService.findIfUserAlreadyClickScore(scoreDto);
    }
}
