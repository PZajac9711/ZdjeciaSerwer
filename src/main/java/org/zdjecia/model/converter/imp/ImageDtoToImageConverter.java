package org.zdjecia.model.converter.imp;

import org.springframework.stereotype.Component;
import org.zdjecia.model.converter.Converter;
import org.zdjecia.model.dto.ImageDto;
import org.zdjecia.model.entities.Image;

import java.sql.Date;

@Component(value = "DtoToImage")
public class ImageDtoToImageConverter implements Converter<ImageDto, Image> {
    @Override
    public Image convert(ImageDto from) {
        Date date = new Date(System.currentTimeMillis());
        return new Image(from.getTitle(),from.getName(),from.getPoints(),date);
    }
}
