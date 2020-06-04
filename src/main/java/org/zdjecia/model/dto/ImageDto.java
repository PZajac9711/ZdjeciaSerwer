package org.zdjecia.model.dto;

public class ImageDto {
    private String title;
    private String name;
    private int points;
    public ImageDto(String title, String name, int points) {
        this.title = title;
        this.name = name;
        this.points = points;
    }

    public ImageDto() {
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

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "ImageDto{" +
                "title='" + title + '\'' +
                ", name='" + name + '\'' +
                ", points=" + points +
                '}';
    }
}
