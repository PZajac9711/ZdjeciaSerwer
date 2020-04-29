package org.zdjecia.services;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FileService {
    boolean saveFile(MultipartFile image) throws IOException;
}
