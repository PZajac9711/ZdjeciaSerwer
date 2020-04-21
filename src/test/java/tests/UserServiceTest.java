package tests;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;
import org.zdjecia.Main;
import org.zdjecia.model.dto.UserDto;
import org.zdjecia.model.entities.User;
import org.zdjecia.model.repository.UserRepository;
import org.zdjecia.services.UserService;
import org.zdjecia.services.imp.UserServiceImp;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;

@SpringBootTest(classes = {UserServiceImp.class,Main.class}) // Main robi za configuration póki co bo trzeba wszystknac bena z password encryptem
@RunWith(SpringRunner.class)
public class UserServiceTest {
    @Autowired
    private UserService userService;
    @MockBean
    private UserRepository userRepository;


    @Before
    public void setUp() {
        User alex = new User("alex","kot");
        Mockito.when(userRepository.findByUserName(alex.getUserName()))
                .thenReturn(alex);

        Mockito.when(userRepository.save(any(User.class)))
                .thenReturn(alex);
    }
    @Test
    public void createUserTest() {
        UserDto userDto = new UserDto("alex","kot");
        assertFalse(userService.createUser(userDto));

        UserDto userDto1 = new UserDto("alexx","kot");
        assertTrue(userService.createUser(userDto1));
    }

}
