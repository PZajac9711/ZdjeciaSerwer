package org.zdjecia.services.imp;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zdjecia.model.dto.ScoreDto;
import org.zdjecia.model.entities.Image;
import org.zdjecia.model.entities.Score;
import org.zdjecia.model.repository.ImageRepository;
import org.zdjecia.model.repository.ScoreRepository;
import org.zdjecia.model.security.configuration.JwtConfig;
import org.zdjecia.services.ScoreService;

import static org.hibernate.bytecode.BytecodeLogger.LOGGER;

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
    public int findIfUserAlreadyClickScore(String imageName,String token) {
        ScoreDto scoreDto = new ScoreDto(getUsernameFromToken(token),imageName);
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
        return image.getPoints();
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
    private String getUsernameFromToken(String token){
            Claims claims;
            token = token.substring(7);
            try {
                claims = Jwts.parser()
                        .setSigningKey(JwtConfig.getSecret())
                        .parseClaimsJws(token)
                        .getBody();
            } catch (Exception e) {
                LOGGER.error("Could not get all claims Token from passed token");
                claims = null;
                return "";
            }
            return claims.get("login",String.class);
    }
}
