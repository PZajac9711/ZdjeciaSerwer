package org.zdjecia.services;

import org.springframework.web.multipart.MultipartFile;
import org.zdjecia.model.dto.ImageDto;

import java.io.IOException;

public interface FileService {
    boolean saveFile(MultipartFile image, ImageDto imageData) throws IOException;
    boolean checkIfFileExist(String fileName);
}
