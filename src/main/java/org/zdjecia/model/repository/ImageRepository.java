package org.zdjecia.model.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.zdjecia.model.entities.Image;

public interface ImageRepository extends CrudRepository<Image,Long> {
    @Query(value = "SELECT * FROM memo.images ORDER BY RANDOM() LIMIT 1",nativeQuery = true)
    Image getRandomImage();
    Image findByTitle(String title);
    Image findByName(String name);
}
