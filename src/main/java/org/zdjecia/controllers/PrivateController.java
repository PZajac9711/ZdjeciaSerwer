package org.zdjecia.controllers;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.zdjecia.model.dto.ImageDto;
import org.zdjecia.model.dto.InsertImageDto;
import org.zdjecia.model.dto.ScoreDto;
import org.zdjecia.model.entities.Image;
import org.zdjecia.model.tag.TagEnum;
import org.zdjecia.services.ImageService;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

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

    @PostMapping(value = "/fileUpload",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Object> uploadFile(@RequestParam("file") MultipartFile file) throws IOException {
        File convertFile = new File("C:\\Users\\Patryk\\Desktop\\Zdjecia\\src\\main\\resources\\static\\images\\"+file.getOriginalFilename());
        convertFile.createNewFile();
        FileOutputStream fileOutputStream = new FileOutputStream(convertFile);
        fileOutputStream.write(file.getBytes());
        fileOutputStream.close();
        return new ResponseEntity<>("x", HttpStatus.OK); // CAŁA METODA DO ZMIANY
    }

    @PostMapping(value = "/insert")
    public void insert(@RequestBody InsertImageDto insertImageDto){
        imageService.insertImage(insertImageDto);
    }

    @GetMapping(value = "/getByTitle")
    public ImageDto getImageByTitle(@RequestParam String title){
        return imageService.findImageByTitle(title);
    }

    @PostMapping(value = "/score") // Pytanie co zwracac ?? czy zwracac true/false i na tej podstawie wywolywac skrypt js ktore odrazu bedzie updatowal wynik na stornie ?
    public void userClickScore(@RequestBody ScoreDto scoreDto){
        imageService.findIfUserAlreadyClickScore(scoreDto);
    }
    @GetMapping(value = "/findByTag")
    public List<ImageDto> findByTag(@RequestParam TagEnum tagEnum){
        return imageService.findImagesByTag(tagEnum);
    }
}
