package org.zdjecia.model.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.zdjecia.model.entities.User;

public interface UserRepository extends CrudRepository<User,Long> {
    User findByUserNameAndUserPassword(String userName, String userPassword);
    User findByUserName (String userName);
}
