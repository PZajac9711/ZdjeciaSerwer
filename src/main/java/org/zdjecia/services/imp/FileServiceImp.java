package org.zdjecia.services.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.zdjecia.model.file.FileHelper;
import org.zdjecia.services.FileService;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@Service(value = "fileService")
public class FileServiceImp implements FileService {
    private final FileHelper fileHelper;
    private final String PLACE_TO_SAVE = System.getProperty("user.dir") + "\\src\\main\\upload\\static\\images\\";

    @Autowired
    public FileServiceImp(@Qualifier("fileImp") FileHelper fileHelper) {
        this.fileHelper = fileHelper;
    }

    @Override
    public String saveFile(MultipartFile image) throws IOException {
        final String newFileName = fileHelper.generateNewFileName(image.getOriginalFilename());
        File newImage = new File(PLACE_TO_SAVE + newFileName);
        newImage.createNewFile();
        FileOutputStream fileOutputStream = new FileOutputStream(newImage);
        fileOutputStream.write(image.getBytes());
        fileOutputStream.close();
        return newFileName;
    }

    @Override
    public boolean checkIfFileExist(String fileName) {
        return new File(PLACE_TO_SAVE + fileName).exists();
    }


}
