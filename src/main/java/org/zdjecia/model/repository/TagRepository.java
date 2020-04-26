package org.zdjecia.model.repository;

import org.springframework.data.repository.CrudRepository;
import org.zdjecia.model.entities.Tag;

public interface TagRepository extends CrudRepository<Tag,Long> {
}
