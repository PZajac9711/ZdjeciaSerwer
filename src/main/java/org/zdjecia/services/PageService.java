package org.zdjecia.services;

import org.zdjecia.model.dto.ImageDto;

import java.util.List;

public interface PageService {
    List<ImageDto> getPage(int pageNumber, String sortyBy);
    Long getNumberOfLastPage();
}
