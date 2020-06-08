package org.zdjecia.model.dto;

import java.util.ArrayList;
import java.util.List;

public class TmpDtoToFix {
    private String title;
    private String name;
    private int points;
    private List<String> tags;

    public TmpDtoToFix(String title, String name, int points, List<String> tags) {
        this.title = title;
        this.name = name;
        this.points = points;
        this.tags = tags;
    }

    public TmpDtoToFix() {
        this.tags = new ArrayList<>();
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
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

    public List<String> getTags() {
        return tags;
    }
    public void add(String tag){
        this.tags.add(tag);
    }
}
