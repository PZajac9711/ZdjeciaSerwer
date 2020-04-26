package org.zdjecia.services;

import org.zdjecia.model.entities.Image;

import java.util.List;

public interface PageService {
    List<Image> getPage(int pageNumber,String sortyBy);
    Long getNumberOfLastPage();
}
