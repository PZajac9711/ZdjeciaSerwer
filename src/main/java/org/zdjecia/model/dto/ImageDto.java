package org.zdjecia.model.dto;

public class ImageDto {
    private final String title;
    private final String name;
    private final int points;

    public ImageDto(String title, String name, int points) {
        this.title = title;
        this.name = name;
        this.points = points;
    }

    public String getTitle() {
        return title;
    }

    public String getName() {
        return name;
    }

    public int getPoints() {
        return points;
    }
}
