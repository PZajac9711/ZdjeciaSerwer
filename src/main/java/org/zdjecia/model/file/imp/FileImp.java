package org.zdjecia.model.file.imp;

import org.springframework.stereotype.Component;
import org.zdjecia.model.file.FileHelper;

import java.util.Date;
import java.util.Random;

@Component(value = "fileImp")
public class FileImp implements FileHelper {
    private final String ALL_VIABLE_CHARS = "0123456789abcdefghijklmnoprstwuxyzABCDEFGHIJKLMNOPRSTWUYZ";
    private final int MAX_LENGTH_RANDOM_FILE_NAME = 32;

    @Override
    public String generateNewFileName(String fileName) {
        StringBuilder randomPrefix = new StringBuilder();
        Random random = new Random();
        for(int i = 0; i < MAX_LENGTH_RANDOM_FILE_NAME; i++){
            randomPrefix.append(ALL_VIABLE_CHARS.charAt(random.nextInt(ALL_VIABLE_CHARS.length())));
        }
        Date date = new Date(System.currentTimeMillis());
        String fileDate = date.toString();
        fileDate = fileDate.replaceAll("\\s+","");
        fileDate = fileDate.replaceAll(":","");
        return randomPrefix+fileDate+getFileExtension(fileName);
    }
    private String getFileExtension(String fileName){
        int beginIndex = fileName.indexOf('.');
        return fileName.substring(beginIndex);
    }
}
