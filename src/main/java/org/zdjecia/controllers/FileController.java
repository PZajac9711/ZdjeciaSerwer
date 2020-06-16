package org.zdjecia.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.zdjecia.model.dto.ImageDto;
import org.zdjecia.services.FileService;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.IOException;

@RestController
public class FileController {
    private final FileService fileService;

    @Autowired
    public FileController(@Qualifier("fileService") FileService fileService) {
        this.fileService = fileService;
    }

    @PostMapping(value = "/uploadFile",consumes = {"multipart/form-data"})
    public ResponseEntity<Void> uploadFile(@RequestPart("imageData") ImageDto insertImageDto,
                                           @RequestPart("image") @Valid @NotNull @NotBlank MultipartFile image) throws IOException {
        boolean isAdded = false;
        if(!image.isEmpty()){
            insertImageDto.setName(image.getOriginalFilename());
            isAdded = fileService.saveFile(image,insertImageDto);
        }
        return isAdded ? new ResponseEntity<>(HttpStatus.CREATED) : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
