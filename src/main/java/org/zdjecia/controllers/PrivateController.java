package org.zdjecia.controllers;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.zdjecia.model.dto.ImageDto;
import org.zdjecia.model.tag.TagEnum;
import org.zdjecia.services.ImageService;
import org.zdjecia.services.PageService;
import org.zdjecia.services.ScoreService;

import java.util.List;

@CrossOrigin
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
    public ResponseEntity<ImageDto> getRandomImage() {
        HttpHeaders httpHeaders = new HttpHeaders();
        return ResponseEntity.ok()
                .headers(httpHeaders)
                .body(imageService.getRandomImage());
    }

    @GetMapping(value = "/getByTitle")
    public List<ImageDto> getImageByTitle(@RequestParam String title) {
        return imageService.findImageByTitle(title);
    }

    @GetMapping(value = "/score")
    public ResponseEntity<Void> userClickScore(@RequestParam String imageName, @RequestHeader (name="Authorization") String token) {
        int score = scoreService.findIfUserAlreadyClickScore(imageName,token);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("actualScore", String.valueOf(score));
        httpHeaders.set("access-control-expose-headers", "actualScore");
        return ResponseEntity.ok()
                .headers(httpHeaders)
                .build();
    }

    @GetMapping(value = "/findByTag")
    public List<ImageDto> findByTag(@RequestParam TagEnum tagEnum) {
        return imageService.findImagesByTag(tagEnum);
    }

    @GetMapping(value = "/page")
    public ResponseEntity<List<ImageDto>> getPageById(@RequestParam(defaultValue = "0") int page) {
        return new ResponseEntity<>(pageService.getPage(page, "imageId"), HttpStatus.OK);
    }

    @GetMapping(value = "/numberLastPage")
    public ResponseEntity<Long> numberOfLastPage() {
        return new ResponseEntity<>(pageService.getNumberOfLastPage(), HttpStatus.OK);
    }

}
