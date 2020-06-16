package org.zdjecia.model.converter.imp;

import org.springframework.stereotype.Component;
import org.zdjecia.model.converter.Converter;
import org.zdjecia.model.dto.ImageDto;
import org.zdjecia.model.entities.Image;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component(value = "imageToDtoList")
public class ImageToDtoListConverter implements Converter<List<Image>, List<ImageDto>> {
    @Override
    public List<ImageDto> convert(List<Image> from) {
        List<String> finalTags = new ArrayList<>();
        List<ImageDto> dtoList = new ArrayList<>();
        for(Image image:from){
            image.getTags().forEach(tag -> finalTags.add(tag.getTagEnum()));
            ImageDto imageDto = new ImageDto(image.getTitle(),image.getName(),image.getPoints(),List.copyOf(finalTags));
            dtoList.add(imageDto);
            finalTags.clear();
        }
        return dtoList;
    }
}
