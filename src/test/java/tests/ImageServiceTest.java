package tests;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.zdjecia.Main;
import org.zdjecia.model.converter.Converter;
import org.zdjecia.model.converter.imp.ImageDtoToImageConverter;
import org.zdjecia.model.converter.imp.ImageToDtoConverter;
import org.zdjecia.model.converter.imp.ImageToDtoListConverter;
import org.zdjecia.model.dto.ImageDto;
import org.zdjecia.model.entities.Image;
import org.zdjecia.model.repository.ImageRepository;
import org.zdjecia.model.repository.ScoreRepository;
import org.zdjecia.model.repository.TagRepository;
import org.zdjecia.services.FileService;
import org.zdjecia.services.ImageService;
import org.zdjecia.services.imp.FileServiceImp;
import org.zdjecia.services.imp.ImageServiceImp;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest(classes = {Main.class}) // Co daje ten main ,że bez tego wywala błąd ???
@RunWith(SpringRunner.class)
public class ImageServiceTest {
    @Autowired
    private ImageService imageService;

    @MockBean
    private ImageRepository imageRepository;
    @MockBean
    private ScoreRepository scoreRepository;
    @MockBean
    private TagRepository tagRepository;
}
