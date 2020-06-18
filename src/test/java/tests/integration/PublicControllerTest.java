package tests.integration;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.zdjecia.Main;
import org.zdjecia.controllers.PublicController;
import org.zdjecia.model.dto.UserDto;
import org.zdjecia.model.dto.UserRegisterDto;
import org.zdjecia.model.entities.User;
import org.zdjecia.model.expresions.RegularExpresions;
import org.zdjecia.model.repository.UserRepository;

import java.nio.charset.Charset;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = {Main.class}) // Co daje ten main ,że bez tego wywala błąd ???
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
public class PublicControllerTest {
    public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(), MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));
    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper mapper;
    @Autowired
    UserRepository userRepository;

    @Test
    public void registerMethodTest() throws Exception {
        //given
        UserRegisterDto userRegisterDto = new UserRegisterDto("ala123", "kot", "ala@wp.pl");

        //when
        mockMvc.perform(post("/register")
                .contentType(APPLICATION_JSON_UTF8)
                .content(mapper.writeValueAsString(userRegisterDto)))
                .andExpect(status().isCreated());

        //then
        Optional<User> userOptional = userRepository.findByUserName(userRegisterDto.getUserName());
        assertFalse(userOptional.isEmpty());
    }

    @Test
    public void loginMethodTest() throws Exception {
        //given
        UserDto userDto = new UserDto("ala", "ala123");

        //when
        MvcResult mvcResult = mockMvc.perform(post("/login")
                .contentType(APPLICATION_JSON_UTF8)
                .content(mapper.writeValueAsString(userDto)))
                .andExpect(status().isOk())
                .andReturn();

        //then
        String content = mvcResult.getResponse().getContentAsString();
        assertTrue(content.length()>169); //169 becouse this is ours token length
    }

    @Test
    public void failLoginMethodTest() throws Exception{
        //given
        UserDto userDto = new UserDto("ala123", "ala123");

        //when
        MvcResult mvcResult = mockMvc.perform(post("/login")
                .contentType(APPLICATION_JSON_UTF8)
                .content(mapper.writeValueAsString(userDto)))
                .andExpect(status().isBadRequest())
                .andReturn();

        //then
        String content = mvcResult.getResponse().getContentAsString();
        assertEquals(0, content.length());
    }

    @Test
    public void failRegisterMethodTest() throws Exception {
        //given
        UserRegisterDto userRegisterDto = new UserRegisterDto("ala", "kot", "ala@wp.pl");

        //when
        mockMvc.perform(post("/register")
                .contentType(APPLICATION_JSON_UTF8)
                .content(mapper.writeValueAsString(userRegisterDto)))
                .andExpect(status().isBadRequest());
    }
}
