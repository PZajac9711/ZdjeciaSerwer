package org.zdjecia.model.entities;

import net.bytebuddy.dynamic.loading.InjectionClassLoader;
import org.zdjecia.model.tag.TagEnum;

import javax.persistence.*;

@Entity
@Table(name = "tags")
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    @Column(name = "image_name")
    private String imageName;
    @Enumerated(EnumType.STRING)
    @Column(name = "tag")
    private TagEnum tagEnum;

    public Tag(String imageName,TagEnum tagEnum){
        this.tagEnum = tagEnum;
        this.imageName = imageName;
    }

    public Tag() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public TagEnum getTagEnum() {
        return tagEnum;
    }

    public void setTagEnum(TagEnum tagEnum) {
        this.tagEnum = tagEnum;
    }
}