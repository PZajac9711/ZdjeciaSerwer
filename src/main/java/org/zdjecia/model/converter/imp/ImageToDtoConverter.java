package org.zdjecia.model.converter.imp;

import org.springframework.stereotype.Component;
import org.zdjecia.model.converter.Converter;
import org.zdjecia.model.dto.ImageDto;
import org.zdjecia.model.entities.Image;

import java.util.ArrayList;
import java.util.List;

@Component(value = "imageToDto")
public class ImageToDtoConverter implements Converter<Image, ImageDto> {
    @Override
    public ImageDto convert(Image from) {
        List<String> finalTags = new ArrayList<>();
        from.getTags().forEach(tag -> finalTags.add(tag.getTagEnum()));
        return new ImageDto(from.getTitle(),from.getName(),from.getPoints(),finalTags);
    }
}
