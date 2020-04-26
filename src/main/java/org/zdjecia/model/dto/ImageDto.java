package org.zdjecia.model.dto;

import org.zdjecia.model.tag.TagEnum;

public class ImageDto {
    private String title;
    private String name;
    private int points;
    private TagEnum tag;
    public ImageDto(String title, String name, int points,TagEnum tag) {
        this.title = title;
        this.name = name;
        this.points = points;
        this.tag = tag;
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

    public TagEnum getTag() {
        return tag;
    }

    @Override
    public String toString() {
        return "ImageDto{" +
                "title='" + title + '\'' +
                ", name='" + name + '\'' +
                ", points=" + points +
                ", tags=" + tag +
                '}';
    }
}
