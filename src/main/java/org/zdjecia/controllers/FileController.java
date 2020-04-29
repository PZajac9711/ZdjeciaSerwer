package org.zdjecia.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.zdjecia.services.FileService;

import java.io.IOException;

@RestController
public class FileController {
    private final FileService fileService;

    @Autowired
    public FileController(@Qualifier("fileService") FileService fileService) {
        this.fileService = fileService;
    }

    @PostMapping(value = "/upload",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> uploadFile(MultipartFile image) throws IOException {
        String fileName = fileService.saveFile(image);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("fileName",fileName);
        return new ResponseEntity<>(httpHeaders,HttpStatus.CREATED);
    }
}
