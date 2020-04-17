package org.zdjecia.model.converter.imp;

import org.springframework.stereotype.Component;
import org.zdjecia.model.converter.Converter;
import org.zdjecia.model.dto.ImageDto;
import org.zdjecia.model.entities.Image;

@Component(value = "DtoToImage")
public class ImageDtoToImageConverter implements Converter<ImageDto, Image> {
    @Override
    public Image convert(ImageDto from) {
        return new Image(from.getTitle(),from.getName(),from.getPoints());
    }
}
