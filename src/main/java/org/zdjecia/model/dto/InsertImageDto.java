package org.zdjecia.model.dto;

import org.zdjecia.model.tag.TagEnum;

import java.util.List;

public class InsertImageDto extends ImageDto{
    private List<TagEnum> tags;

    public InsertImageDto() {
    }

    public InsertImageDto(String title, String name, int points, List<TagEnum> tags) {
        super(title, name, points);
        this.tags = tags;
    }

    public List<TagEnum> getTags() {
        return tags;
    }
}
