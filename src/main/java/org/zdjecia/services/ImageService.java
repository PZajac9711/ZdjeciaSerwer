package org.zdjecia.services;

import org.zdjecia.model.dto.ImageDto;
import org.zdjecia.model.dto.InsertImageDto;
import org.zdjecia.model.dto.ScoreDto;
import org.zdjecia.model.tag.TagEnum;

import java.util.List;

public interface ImageService {
    ImageDto getRandomImage();
    void insertImage(InsertImageDto insertImageDto);
    ImageDto findImageByTitle(String title);
    void findIfUserAlreadyClickScore(ScoreDto scoreDto);
    List<ImageDto> findImagesByTag(TagEnum tagEnum);
}
