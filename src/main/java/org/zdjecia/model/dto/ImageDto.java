package org.zdjecia.model.dto;

import java.util.List;

public class ImageDto {
    private String title;
    private String name;
    private int points;
    private List<String> tags;

    public ImageDto(String title, String name, int points, List<String> tags) {
        this.title = title;
        this.name = name;
        this.points = points;
        this.tags = tags;
    }

    public ImageDto() {
    }

    public List<String> getTags() {
        return tags;
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
                ", tags=" + tags +
                '}';
    }
}
