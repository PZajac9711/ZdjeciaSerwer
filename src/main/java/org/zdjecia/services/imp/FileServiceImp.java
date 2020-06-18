package org.zdjecia.services.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.zdjecia.model.converter.Converter;
import org.zdjecia.model.dto.ImageDto;
import org.zdjecia.model.entities.Image;
import org.zdjecia.model.entities.Tag;
import org.zdjecia.model.file.FileHelper;
import org.zdjecia.model.repository.ImageRepository;
import org.zdjecia.model.repository.TagRepository;
import org.zdjecia.model.tag.TagEnum;
import org.zdjecia.services.FileService;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@Service(value = "fileService")
public class FileServiceImp implements FileService {
    private final FileHelper fileHelper;
    private final String PLACE_TO_SAVE = System.getProperty("user.dir") + "\\src\\main\\upload\\static\\images\\";
    private final Converter<ImageDto, Image> converterImageDtoToImage;

    private TagRepository tagRepository;
    private ImageRepository imageRepository;

    @Autowired
    public FileServiceImp(@Qualifier("DtoToImage") Converter<ImageDto, Image> converterImageDtoToImage,@Qualifier("fileImp") FileHelper fileHelper, TagRepository tagRepository, ImageRepository imageRepository) {
        this.fileHelper = fileHelper;
        this.tagRepository = tagRepository;
        this.converterImageDtoToImage = converterImageDtoToImage;
        this.imageRepository = imageRepository;
    }

    @Override
    public boolean saveFile(MultipartFile image, ImageDto imageData) throws IOException {
        final String newFileName = fileHelper.generateNewFileName(image.getOriginalFilename());
        File newImage = new File(PLACE_TO_SAVE + newFileName);
        newImage.createNewFile();
        FileOutputStream fileOutputStream = new FileOutputStream(newImage);
        fileOutputStream.write(image.getBytes());
        fileOutputStream.close();
        return insertImage(newImage.getName(),imageData);
    }

    @Override
    public boolean checkIfFileExist(String fileName) {
        return new File(PLACE_TO_SAVE + fileName).exists();
    }


    private boolean insertImage(String name,ImageDto imageData) {
        imageData.setName(name);
        if(checkIfFileExist(name)){
            Image image = converterImageDtoToImage.convert(imageData);
            imageRepository.save(image);
            imageData.getTags()
                    .forEach(tag -> tagRepository.save(new Tag(name,Enum.valueOf(TagEnum.class,tag))));
            return true;
        }
        return false;
    }
}
