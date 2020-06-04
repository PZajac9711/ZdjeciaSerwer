package org.zdjecia.services;

import org.zdjecia.model.dto.ImageDto;
import org.zdjecia.model.dto.InsertImageDto;
import org.zdjecia.model.tag.TagEnum;

import java.util.List;

public interface ImageService {
    ImageDto getRandomImage();
    List<ImageDto> findImageByTitle(String title);
    List<ImageDto> findImagesByTag(TagEnum tagEnum);
}
