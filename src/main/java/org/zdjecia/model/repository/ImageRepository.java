package org.zdjecia.model.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.zdjecia.model.entities.Image;

import java.util.List;
//Joiny przez obiekty
public interface ImageRepository extends CrudRepository<Image,Long>, PagingAndSortingRepository<Image,Long> {
    @Query(value = "SELECT u FROM Image u ORDER BY function('RANDOM')")
    List<Image> getRandomImage();
    List<Image> findByTitle(String title);
    Image findByName(String name);
    @Query(value = "SELECT u FROM Image u WHERE u.name IN(SELECT u.imageName FROM Tag u WHERE u.tagEnum = :tag)")
    List<Image> findByTag(@Param("tag") String tag);
}
