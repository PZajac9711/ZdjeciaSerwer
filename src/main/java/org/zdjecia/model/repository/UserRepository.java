package org.zdjecia.model.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.zdjecia.model.entities.User;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User,Long> {
    User findByUserNameAndUserPassword(String userName, String userPassword);
    Optional<User> findByUserName (String userName);
}
