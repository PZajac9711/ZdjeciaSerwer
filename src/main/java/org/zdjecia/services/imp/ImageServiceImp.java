package org.zdjecia.services.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.zdjecia.model.converter.Converter;
import org.zdjecia.model.dto.ImageDto;
import org.zdjecia.model.entities.Image;
import org.zdjecia.model.repository.ImageRepository;
import org.zdjecia.model.tag.TagEnum;
import org.zdjecia.services.ImageService;

import java.util.List;

@Service(value = "imageService")
public class ImageServiceImp implements ImageService {
    private final ImageRepository imageRepository;


    private final Converter<Image, ImageDto> converterImageToDto;
    private final Converter<List<Image>, List<ImageDto>> converterImageToDtoList;

    @Autowired
    public ImageServiceImp(ImageRepository imageRepository,
                           @Qualifier("imageToDto") Converter<Image, ImageDto> converterImageToDto,
                           @Qualifier("imageToDtoList") Converter<List<Image>, List<ImageDto>> converterImageToDtoList) {
        this.imageRepository = imageRepository;
        this.converterImageToDto = converterImageToDto;
        this.converterImageToDtoList = converterImageToDtoList;
    }

    @Override
    public ImageDto getRandomImage() {
        return converterImageToDto.convert(imageRepository.getRandomImage().get(0));
    }

    @Override
    public List<ImageDto> findImageByTitle(String title) {
        List<ImageDto> imageDto = converterImageToDtoList.convert(imageRepository.findByTitle(title));
        return imageDto;
    }

    @Override
    public List<ImageDto> findImagesByTag(TagEnum tagEnum) {
        return converterImageToDtoList.convert(imageRepository.findByTag(tagEnum.getTagName()));
    }

}
