package org.zdjecia.services.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.zdjecia.model.converter.Converter;
import org.zdjecia.model.dto.ImageDto;
import org.zdjecia.model.dto.InsertImageDto;
import org.zdjecia.model.dto.TmpDtoToFix;
import org.zdjecia.model.entities.Image;
import org.zdjecia.model.entities.Tag;
import org.zdjecia.model.repository.ImageRepository;
import org.zdjecia.model.repository.TagRepository;
import org.zdjecia.model.tag.TagEnum;
import org.zdjecia.services.FileService;
import org.zdjecia.services.ImageService;

import java.util.List;

@Service(value = "imageService")
public class ImageServiceImp implements ImageService {
    private final ImageRepository imageRepository;
    private final TagRepository tagRepository;
    private final FileService fileService;


    private final Converter<Image, ImageDto> converterImageToDto;
    private final Converter<ImageDto, Image> converterImageDtoToImage;
    private final Converter<List<Image>, List<ImageDto>> converterImageToDtoList;

    @Autowired
    public ImageServiceImp(ImageRepository imageRepository,
                           TagRepository tagRepository,
                           @Qualifier("fileService") FileService fileService,
                           @Qualifier("imageToDto") Converter<Image, ImageDto> converterImageToDto,
                           @Qualifier("DtoToImage") Converter<ImageDto, Image> converterImageDtoToImage,
                           @Qualifier("imageToDtoList") Converter<List<Image>, List<ImageDto>> converterImageToDtoList) {
        this.imageRepository = imageRepository;
        this.fileService = fileService;
        this.converterImageToDto = converterImageToDto;
        this.converterImageDtoToImage = converterImageDtoToImage;
        this.tagRepository = tagRepository;
        this.converterImageToDtoList = converterImageToDtoList;
    }

    @Override
    public TmpDtoToFix getRandomImage() {
        ImageDto imageDto = converterImageToDto.convert(imageRepository.getRandomImage().get(0));
        List<Tag> tags = tagRepository.getTagsByImageName(imageDto.getName());
        TmpDtoToFix tmpDtoToFix = new TmpDtoToFix();
        tmpDtoToFix.setTitle(imageDto.getTitle());
        tmpDtoToFix.setPoints(imageDto.getPoints());
        tmpDtoToFix.setName(imageDto.getName());
        for(Tag tag: tags){
            tmpDtoToFix.add(tag.getTagEnum());
        }
        return tmpDtoToFix;
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
