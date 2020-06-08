package org.zdjecia.services;

import org.zdjecia.model.dto.ImageDto;
import org.zdjecia.model.dto.TmpDtoToFix;
import org.zdjecia.model.entities.Image;

import java.util.List;

public interface PageService {
    List<TmpDtoToFix> getPage(int pageNumber, String sortyBy);
    Long getNumberOfLastPage();
}
