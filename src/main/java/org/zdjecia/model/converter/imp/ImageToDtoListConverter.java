package org.zdjecia.model.converter.imp;

import org.springframework.stereotype.Component;
import org.zdjecia.model.converter.Converter;
import org.zdjecia.model.dto.ImageDto;
import org.zdjecia.model.entities.Image;

import java.util.List;
import java.util.stream.Collectors;

@Component(value = "imageToDtoList")
public class ImageToDtoListConverter implements Converter<List<Image>, List<ImageDto>> {
    @Override
    public List<ImageDto> convert(List<Image> from) {
        return from.stream()
                .map(img -> new ImageDto(img.getTitle(),img.getName(),img.getPoints()))
                .collect(Collectors.toList());
    }
}
