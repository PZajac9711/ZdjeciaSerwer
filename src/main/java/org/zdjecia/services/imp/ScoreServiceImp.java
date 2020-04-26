package org.zdjecia.services.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zdjecia.model.dto.ScoreDto;
import org.zdjecia.model.entities.Image;
import org.zdjecia.model.entities.Score;
import org.zdjecia.model.repository.ImageRepository;
import org.zdjecia.model.repository.ScoreRepository;
import org.zdjecia.services.ScoreService;

@Service(value = "scoreService")
public class ScoreServiceImp implements ScoreService {
    private ScoreRepository scoreRepository;
    private ImageRepository imageRepository;
    @Autowired
    public ScoreServiceImp(ScoreRepository scoreRepository, ImageRepository imageRepository) {
        this.scoreRepository = scoreRepository;
        this.imageRepository = imageRepository;
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
