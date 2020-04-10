package org.zdjecia.model.repository;

import org.springframework.data.repository.CrudRepository;
import org.zdjecia.model.entities.User;

public interface UserRepository extends CrudRepository<User,Long> {
}
