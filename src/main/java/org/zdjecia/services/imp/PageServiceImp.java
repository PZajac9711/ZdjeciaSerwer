package org.zdjecia.services.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.zdjecia.model.converter.Converter;
import org.zdjecia.model.dto.ImageDto;
import org.zdjecia.model.entities.Image;
import org.zdjecia.model.repository.ImageRepository;
import org.zdjecia.services.PageService;

import java.util.ArrayList;
import java.util.List;

@Service(value = "pageService")
public class PageServiceImp implements PageService {
    private final ImageRepository imageRepository;
    private final int MAX_PAGE_ON_SINGLE_PAGE = 5;
    private final Converter<List<Image>, List<ImageDto>> imageListToDtoList;

    @Autowired
    public PageServiceImp(ImageRepository imageRepository,
                          @Qualifier("imageToDtoList") Converter<List<Image>, List<ImageDto>> imageListToDtoList) {
        this.imageRepository = imageRepository;
        this.imageListToDtoList = imageListToDtoList;
    }

    @Override
    public List<ImageDto> getPage(int pageNumber,String sortBy) {
        if(pageNumber > getNumberOfLastPage()){
            long tmp = getNumberOfLastPage()-1;
            pageNumber = (int)tmp;
        }
        PageRequest paging = PageRequest.of(pageNumber, MAX_PAGE_ON_SINGLE_PAGE, Sort.by(sortBy));
        Page<Image> pagedResult = imageRepository.findAll(paging);
        if(pagedResult.hasContent()) {
            return imageListToDtoList.convert(pagedResult.getContent());
        } else {
            return new ArrayList<ImageDto>();
        }
    }

    @Override
    public Long getNumberOfLastPage() {
        return imageRepository.count()/MAX_PAGE_ON_SINGLE_PAGE;
    }
}
