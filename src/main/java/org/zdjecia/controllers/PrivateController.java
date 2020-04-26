package org.zdjecia.controllers;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.zdjecia.model.dto.ImageDto;
import org.zdjecia.model.dto.ScoreDto;
import org.zdjecia.model.entities.Image;
import org.zdjecia.model.tag.TagEnum;
import org.zdjecia.services.ImageService;
import org.zdjecia.services.PageService;
import org.zdjecia.services.ScoreService;

import java.util.List;

@RestController
public class PrivateController {
    private final ImageService imageService;
    private final ScoreService scoreService;
    private final PageService pageService;
    public PrivateController(@Qualifier("imageService") ImageService imageService,
                             @Qualifier("scoreService") ScoreService scoreService,
                             @Qualifier("pageService") PageService pageService) {
        this.imageService = imageService;
        this.scoreService = scoreService;
        this.pageService = pageService;
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

    @GetMapping(value = "/page")
    public ResponseEntity<List<Image>> getPageById(@RequestParam(defaultValue = "0") int page){
        return new ResponseEntity<>(pageService.getPage(page,"imageId"), HttpStatus.OK);
    }
    @GetMapping(value = "/numberLastPage")
    public ResponseEntity<Long> numberOfLastPage(){
        return new ResponseEntity<>(pageService.getNumberOfLastPage(),HttpStatus.OK);
    }
}
