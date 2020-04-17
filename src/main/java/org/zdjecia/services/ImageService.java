package org.zdjecia.services;

import org.zdjecia.model.dto.ImageDto;
import org.zdjecia.model.entities.Image;

public interface ImageService {
    ImageDto getRandomImage();
    void insertImage(Image image);
    ImageDto findImageByTitle(String title);
}
