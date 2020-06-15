package org.zdjecia.model.entities;

import net.bytebuddy.dynamic.loading.InjectionClassLoader;
import org.zdjecia.model.tag.TagEnum;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "tags")
public class Tag implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "image_name")
    private String imageName;
    @Column(name = "tag")
    private String tagEnum;

    @ManyToOne
    @JoinColumn(name = "image_name",referencedColumnName = "name",insertable = false,updatable = false)
    private Image imageTag;

    public Image getImageTag() {
        return imageTag;
    }

    public void setImageTag(Image imageTag) {
        this.imageTag = imageTag;
    }

    public Tag(String imageName, TagEnum tagEnum){
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

    @Override
    public String toString() {
        return "Tag{" +
                "id=" + id +
                ", imageName='" + imageName + '\'' +
                ", tagEnum='" + tagEnum + '\'' +
                '}';
    }
}
