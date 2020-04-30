package org.zdjecia.model.entities;

import net.bytebuddy.dynamic.loading.InjectionClassLoader;
import org.zdjecia.model.tag.TagEnum;

import javax.persistence.*;

@Entity
@Table(name = "tags",schema = "memo")
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    @Column(name = "image_name")
    private String imageName;
    @Column(name = "tag")
    private String tagEnum;

    @ManyToOne
    @JoinColumn(name = "image_name",insertable = false,updatable = false)
    private Image image;

    public Tag(String imageName,TagEnum tagEnum){
        this.tagEnum = tagEnum.getTagName();
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

    public String getTagEnum() {
        return tagEnum;
    }

    public void setTagEnum(TagEnum tagEnum) {
        this.tagEnum = tagEnum.getTagName();
    }
}
