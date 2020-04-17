package org.zdjecia.services;

import org.zdjecia.model.dto.ImageDto;
import org.zdjecia.model.entities.Image;

public interface ImageService {
    ImageDto getRandomImage();
    void insertImage(ImageDto imageDto);
    ImageDto findImageByTitle(String title);
}
