package org.zdjecia.model.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.zdjecia.model.entities.Image;
import org.zdjecia.model.tag.TagEnum;

import java.util.List;

public interface ImageRepository extends CrudRepository<Image,Long> {
    @Query(value = "SELECT * FROM memo.images ORDER BY RANDOM() LIMIT 1",nativeQuery = true)
    Image getRandomImage();
    Image findByTitle(String title);
    Image findByName(String name);
    @Query(value = "SELECT * FROM memo.images WHERE name IN(SELECT image_name FROM memo.tags u WHERE u.tag = :tag)",nativeQuery = true)
    List<Image> findByTag(@Param("tag") String tag);
}
