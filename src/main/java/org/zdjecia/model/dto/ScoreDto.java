package org.zdjecia.model.dto;

public class ScoreDto {
    private String userName;
    private String imageName;

    public ScoreDto() {
    }

    public ScoreDto(String userName, String imageName) {
        this.userName = userName;
        this.imageName = imageName;
    }

    public String getUserName() {
        return userName;
    }

    public String getImageName() {
        return imageName;
    }
}
