package org.zdjecia.controllers;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;
import org.zdjecia.model.dto.ImageDto;
import org.zdjecia.model.dto.ScoreDto;
import org.zdjecia.model.tag.TagEnum;
import org.zdjecia.services.ImageService;
import org.zdjecia.services.ScoreService;

import java.util.List;

@RestController
public class PrivateController {
    private final ImageService imageService;
    private final ScoreService scoreService;

    public PrivateController(@Qualifier("imageService") ImageService imageService,
                             @Qualifier("scoreService") ScoreService scoreService) {
        this.imageService = imageService;
        this.scoreService = scoreService;
    }

    @GetMapping(value = "/random")
    public ImageDto getRandomImage(){
        return imageService.getRandomImage();
    }

    @GetMapping(value = "/getByTitle") // DO ZMIANY bo title nie jest unikalne i moze zwrocic kilka rezultow !!
    public ImageDto getImageByTitle(@RequestParam String title){
        return imageService.findImageByTitle(title);
    }

    @PostMapping(value = "/score") // Pytanie co zwracac ?? czy zwracac true/false i na tej podstawie wywolywac skrypt js ktore odrazu bedzie updatowal wynik na stornie ?
    public void userClickScore(@RequestBody ScoreDto scoreDto){//poczytac o http w header
        scoreService.findIfUserAlreadyClickScore(scoreDto);
    }

    @GetMapping(value = "/findByTag")
    public List<ImageDto> findByTag(@RequestParam TagEnum tagEnum){
        return imageService.findImagesByTag(tagEnum);
    }
}
