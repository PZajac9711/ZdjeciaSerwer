package org.zdjecia.model.dto;

public class ScoreDto {
    private final String userName;
    private final String imageTitle;

    public ScoreDto(String userName, String imageTitle) {
        this.userName = userName;
        this.imageTitle = imageTitle;
    }

    public String getUserName() {
        return userName;
    }

    public String getImageTitle() {
        return imageTitle;
    }
}
