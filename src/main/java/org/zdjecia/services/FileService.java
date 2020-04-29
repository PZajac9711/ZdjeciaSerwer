package org.zdjecia.services;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FileService {
    String saveFile(MultipartFile image) throws IOException;
    boolean checkIfFileExist(String fileName);
}
