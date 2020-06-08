package org.zdjecia.model.repository;

import org.springframework.data.repository.CrudRepository;
import org.zdjecia.model.entities.Image;
import org.zdjecia.model.entities.Tag;

import java.util.List;
import java.util.Optional;

public interface TagRepository extends CrudRepository<Tag,Long> {
    List<Tag> getTagsByImageName(String imageName);
}
