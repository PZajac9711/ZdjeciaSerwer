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
import org.zdjecia.model.converter.Converter;
import org.zdjecia.model.converter.imp.ImageDtoToImageConverter;
import org.zdjecia.model.converter.imp.ImageToDtoConverter;
import org.zdjecia.model.converter.imp.ImageToDtoListConverter;
import org.zdjecia.model.dto.ImageDto;
import org.zdjecia.model.entities.Image;
import org.zdjecia.model.repository.ImageRepository;
import org.zdjecia.model.repository.ScoreRepository;
import org.zdjecia.model.repository.TagRepository;
import org.zdjecia.services.ImageService;
import org.zdjecia.services.imp.ImageServiceImp;

import java.sql.Date;
import java.util.List;

@SpringBootTest(classes = {ImageServiceImp.class, ImageToDtoConverter.class, ImageToDtoListConverter.class, ImageDtoToImageConverter.class})
@RunWith(SpringRunner.class)
public class ImageServiceTest {
    @Autowired
    private ImageService imageService;

    //CONVERTERY TEZ RACZEJ POWINNY BYC MOCKAMI
    @Autowired
    @Qualifier("imageToDto")
    private Converter<Image, ImageDto> converterImageToDto;
    @Autowired
    @Qualifier("DtoToImage")
    private Converter<ImageDto, Image> converterImageDtoToImage;
    @Autowired
    @Qualifier("imageToDtoList")
    private  Converter<List<Image>, List<ImageDto>> converterImageToDtoList;


    @MockBean
    private ImageRepository imageRepository;
    @MockBean
    private ScoreRepository scoreRepository;
    @MockBean
    private TagRepository tagRepository;


    @Before
    public void setUp(){
        Date date = new Date(System.currentTimeMillis());
        Image image = new Image("imagename","imagename",0,date);
        Mockito.when(imageRepository.findByName("imagename"))
                .thenReturn(image);
        Mockito.when(imageRepository.getRandomImage())
                .thenReturn(image);
    }

    @Test
    public void shouldReturnImage(){
        Date date = new Date(System.currentTimeMillis());
        ImageDto image = new ImageDto("imagename","imagename",0);
        Assert.assertEquals(image.getName(),imageService.getRandomImage().getName());
    }
}
