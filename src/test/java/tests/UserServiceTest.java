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
import org.zdjecia.model.dto.UserRegisterDto;
import org.zdjecia.model.entities.User;
import org.zdjecia.model.repository.UserRepository;
import org.zdjecia.services.UserService;
import org.zdjecia.services.imp.UserServiceImp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@SpringBootTest(classes = {UserServiceImp.class, Main.class})
// Main robi za configuration póki co bo trzeba wszystknac bena z password encryptem
@RunWith(SpringRunner.class)
public class UserServiceTest {
    @Autowired
    private UserService userService;
    @MockBean
    private UserRepository userRepository;

    private List<String> userList;

    @Before
    public void setUp() {
        this.userList = new ArrayList<>(
                Arrays.asList(
                        "alex",
                        "barbara",
                        "janek123",
                        "alex321",
                        "dzieciol997"
                )
        );
    }

    @Test
    public void createUserTestShouldReturnFalse() {
        // given
        UserRegisterDto userDto = new UserRegisterDto("alex", "x", "x");
        Optional<User> user = this.userList.contains("alex") ? Optional.of(new User(this.userList.get(this.userList.indexOf("alex")), "x", "x")) : Optional.empty();
        Mockito.when(userRepository.findByUserName("alex"))
                .thenReturn(user);

        // when
        boolean isCreated = userService.createUser(userDto);

        // then
        assertFalse(isCreated);
    }

    @Test
    public void createUserTestShouldReturnTrue() {
        // given
        UserRegisterDto userDto = new UserRegisterDto("alexx", "x", "email@wp.pl");
        Optional<User> user = this.userList.contains("alexx") ? Optional.of(new User(this.userList.get(this.userList.indexOf("alexx")), "x", "x")) : Optional.empty();
        Mockito.when(userRepository.findByUserName("alexx"))
                .thenReturn(user);

        // when
        boolean isCreated = userService.createUser(userDto);

        // then
        assertTrue(isCreated);
    }
}
