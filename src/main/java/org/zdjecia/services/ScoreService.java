package org.zdjecia.services;

import org.zdjecia.model.dto.ScoreDto;

public interface ScoreService {
    void findIfUserAlreadyClickScore(ScoreDto scoreDto);
}
