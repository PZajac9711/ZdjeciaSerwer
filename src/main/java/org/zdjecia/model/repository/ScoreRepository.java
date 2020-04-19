package org.zdjecia.model.repository;

import org.springframework.data.repository.CrudRepository;
import org.zdjecia.model.entities.Score;

public interface ScoreRepository extends CrudRepository<Score,Long> {
    Score findByImageNameAndUserName(String imageName,String userName);
}
