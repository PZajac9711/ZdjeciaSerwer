package org.zdjecia.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;
import org.zdjecia.services.FileService;

import java.io.IOException;

@Controller
public class FileController {
    private final FileService fileService;

    @Autowired
    public FileController(@Qualifier("fileService") FileService fileService) {
        this.fileService = fileService;
    }

    @PostMapping(value = "/upload",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> uploadFile(MultipartFile image) throws IOException {
        fileService.saveFile(image);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("isCreated","yes"); // To do zmiany z tym hederem tylko tak dla zabawy dodane
        return ResponseEntity.ok()
                .headers(httpHeaders)
                .build();
    }
}
