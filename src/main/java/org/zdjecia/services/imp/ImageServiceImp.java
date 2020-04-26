package org.zdjecia.services.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.zdjecia.model.converter.Converter;
import org.zdjecia.model.dto.ImageDto;
import org.zdjecia.model.dto.ScoreDto;
import org.zdjecia.model.entities.Image;
import org.zdjecia.model.entities.Score;
import org.zdjecia.model.repository.ImageRepository;
import org.zdjecia.model.repository.ScoreRepository;
import org.zdjecia.services.ImageService;

@Service(value = "imageService")
public class ImageServiceImp implements ImageService {
    private final ImageRepository imageRepository;
    private final ScoreRepository scoreRepository;
    private final Converter<Image, ImageDto> converterImageToDto;
    private final Converter<ImageDto, Image> converterImageDtoToImage;

    @Autowired
    public ImageServiceImp(ImageRepository imageRepository,
                           ScoreRepository scoreRepository,
                           @Qualifier("imageToDto") Converter<Image, ImageDto> converterImageToDto,
                           @Qualifier("DtoToImage") Converter<ImageDto, Image> converterImageDtoToImage) {
        this.imageRepository = imageRepository;
        this.converterImageToDto = converterImageToDto;
        this.converterImageDtoToImage = converterImageDtoToImage;
        this.scoreRepository = scoreRepository;
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

    @Override
    public void findIfUserAlreadyClickScore(ScoreDto scoreDto) {
        Image image = imageRepository.findByName(scoreDto.getImageName());
        Score score = scoreRepository.findByImageNameAndUserName(scoreDto.getImageName(),scoreDto.getUserName().toLowerCase());
        if(score == null){
            insertScore(scoreDto,image.getImageId());
            image.increaseScore();
        }
        else{
            deleteScore(score.getScoreId());
            image.decreaseScore();
        }
        imageRepository.save(image);
    }
    private void deleteScore(Long id){
        scoreRepository.deleteById(id);
    }
    private void insertScore(ScoreDto scoreDto, Long imageId){
        Score score = new Score.Builder()
                .imageName(scoreDto.getImageName())
                .imageId(imageId)
                .userName(scoreDto.getUserName().toLowerCase())
                .build();
        scoreRepository.save(score);
    }

}
