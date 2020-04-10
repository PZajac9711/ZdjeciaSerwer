package org.zdjecia.services.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zdjecia.model.entities.User;
import org.zdjecia.model.repository.UserRepository;
import org.zdjecia.services.UserService;

@Service(value = "userServiceImp")
public class UserServiceImp implements UserService {
    private UserRepository userRepository;

    @Autowired
    public UserServiceImp(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


}
