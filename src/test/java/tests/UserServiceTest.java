package tests;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.zdjecia.Main;
import org.zdjecia.model.dto.UserDto;
import org.zdjecia.model.dto.UserRegisterDto;
import org.zdjecia.model.entities.User;
import org.zdjecia.model.repository.UserRepository;
import org.zdjecia.services.UserService;
import org.zdjecia.services.imp.UserServiceImp;

import java.util.Optional;

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
        User alex = new User("alex","kot", "email");

        Mockito.when(userRepository.findByUserName(alex.getUserName()))
                .thenReturn(Optional.of(alex));

        Mockito.when(userRepository.save(any(User.class)))
                .thenReturn(alex);
    }
    @Test
    public void createUserTest() {
        UserRegisterDto userDto = new UserRegisterDto("alex","kot","email");
        assertFalse(userService.createUser(userDto));

        UserRegisterDto userDto1 = new UserRegisterDto("alexx","kot","email");
        assertTrue(userService.createUser(userDto1));
    }

}
