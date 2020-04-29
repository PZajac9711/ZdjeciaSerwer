package org.zdjecia.model.file.imp;

import org.springframework.stereotype.Component;
import org.zdjecia.model.file.FileHelper;

import java.util.Date;
import java.util.Random;

@Component(value = "fileImp")
public class FileImp implements FileHelper {
    private final String allViableChars = "0123456789abcdefghijklmnoprstwuxyzABCDEFGHIJKLMNOPRSTWUYZ";

    @Override
    public String generateNewFileName(String fileName) {
        StringBuilder randomPrefix = new StringBuilder();
        Random random = new Random();
        for(int i = 0; i < 32; i++){
            randomPrefix.append(allViableChars.charAt(random.nextInt(allViableChars.length())));
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
