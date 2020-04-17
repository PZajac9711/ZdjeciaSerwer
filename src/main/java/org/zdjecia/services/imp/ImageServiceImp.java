package org.zdjecia.services.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.zdjecia.model.converter.Converter;
import org.zdjecia.model.dto.ImageDto;
import org.zdjecia.model.entities.Image;
import org.zdjecia.model.repository.ImageRepository;
import org.zdjecia.services.ImageService;

@Service(value = "imageService")
public class ImageServiceImp implements ImageService {
    private final ImageRepository imageRepository;
    private final Converter<Image, ImageDto> converterImageToDto;
    private final Converter<ImageDto, Image> converterImageDtoToImage;

    @Autowired
    public ImageServiceImp(ImageRepository imageRepository,
                           @Qualifier("imageToDto") Converter<Image, ImageDto> converterImageToDto,
                           @Qualifier("DtoToImage") Converter<ImageDto, Image> converterImageDtoToImage) {
        this.imageRepository = imageRepository;
        this.converterImageToDto = converterImageToDto;
        this.converterImageDtoToImage = converterImageDtoToImage;
    }


    @Override
    public ImageDto getRandomImage() {
        ImageDto imageDto = converterImageToDto.convert(imageRepository.getRandomImage());
        return imageDto;
    }

    @Override
    public void insertImage(ImageDto imageDto) {
        Image image = converterImageDtoToImage.convert(imageDto);
        imageRepository.save(image);
    }

    @Override
    public ImageDto findImageByTitle(String title) {
        ImageDto imageDto = converterImageToDto.convert(imageRepository.findByTitle(title));
        return imageDto;
    }


}
