package org.zdjecia.model.entities;

import javax.persistence.*;

@Entity
@Table(name = "images")
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_image")
    private Long imageId;
    @Column(name = "title")
    private String title; // image title
    @Column(name = "name")
    private String name; // image name
    @Column(name = "points")
    private int points; // how much points have single image


    public Image(String title, String name,int points) {
        this.title = title;
        this.points = points;
        this.name = name;
    }

    public Image() {
    }

    public Long getImageId() {
        return imageId;
    }

    public void setImageId(Long imageId) {
        this.imageId = imageId;
    }

    public String getTitle() {
        return title;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    @Override
    public String toString() {
        return "Image{" +
                "imageId=" + imageId +
                ", title='" + title + '\'' +
                ", name='" + name + '\'' +
                ", points=" + points +
                '}';
    }

    public void increaseScore(){
        this.points += 1;
    }
    public void decreaseScore(){
        this.points -= 1;
    }
}
