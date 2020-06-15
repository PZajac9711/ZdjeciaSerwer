package org.zdjecia.model.entities;

import javax.persistence.*;

@Entity
@Table(name = "score")
public class Score {
    @Id
    @Column(name = "score_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long scoreId;
    @Column(name = "image_id")
    private Long imageId;
    @Column(name = "image_name")
    private String imageName;
    @Column(name = "user_name")
    private String userName;

    public Score() {
    }

    public Score(Builder builder){
        this.imageId = builder.imageId;
        this.imageName = builder.imageName;
        this.userName = builder.userName;
    }
    public Long getScoreId() {
        return scoreId;
    }

    public Long getImageId() {
        return imageId;
    }

    public String getImageName() {
        return imageName;
    }

    public String getUserName() {
        return userName;
    }
    public static final class Builder {
        private Long imageId;
        private String imageName;
        private String userName;

        public Builder() {
        }

        public Builder(Long imageId, String imageName, String userName) {
            this.imageId = imageId;
            this.imageName = imageName;
            this.userName = userName;
        }

        public Score.Builder imageId(Long imageId){
            this.imageId = imageId;
            return this;
        }
        public Score.Builder imageName(String imageName){
            this.imageName = imageName;
            return this;
        }
        public Score.Builder userName(String userName){
            this.userName = userName;
            return this;
        }
        public Score build(){
            return new Score(this);
        }
    }
}
