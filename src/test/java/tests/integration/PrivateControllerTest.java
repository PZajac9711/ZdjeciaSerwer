package tests.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jayway.jsonpath.JsonPath;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.zdjecia.Main;
import org.zdjecia.controllers.PrivateController;
import org.zdjecia.model.dto.ImageDto;
import org.zdjecia.model.security.imp.JwtGenerateImp;

import java.lang.reflect.Type;
import java.nio.charset.Charset;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = {Main.class})
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
public class PrivateControllerTest {
    public static final MediaType MEDIA_TYPE = new MediaType(MediaType.APPLICATION_JSON.getType(), MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));
    @Autowired
    MockMvc mockMvc;
    @Autowired
    PrivateController privateController;
    @Autowired
    ObjectMapper mapper;
    JwtGenerateImp generateToken = new JwtGenerateImp();

    @Test
    public void randomMethodTest() throws Exception {
        //given
        String token = generateToken.generateToken("testy");

        //when
        MvcResult mvcResult = mockMvc.perform(get("/random")
                .contentType(MEDIA_TYPE)
                .header("authorization", "Bearer " + token))
                .andExpect(status().isOk())
                .andReturn();
        //then

        String name = JsonPath.read(mvcResult.getResponse().getContentAsString(), "$.name");
        assertTrue(!name.equals("") && name.contains("."));
    }

    @Test
    public void getByTitleMethodTest() throws Exception {
        //given
        String token = generateToken.generateToken("testy");

        //when
        MvcResult mvcResult = mockMvc.perform(get("/getByTitle?title=title")
                .contentType(MEDIA_TYPE)
                .header("authorization", "Bearer " + token))
                .andExpect(status().isOk())
                .andReturn();

        //then
        Type typeList = new TypeToken<ArrayList<ImageDto>>() {}.getType();
        ArrayList<ImageDto> images = new Gson().fromJson(mvcResult.getResponse().getContentAsString(), typeList);
        boolean result = images.stream().allMatch(image -> image.getTitle().equals("title"));
        Assert.assertTrue(result && images.size() == 5); // size == 5 becouse we got 5 images in db that match
    }

    @Test
    public void scoreMethodTest() throws Exception {
        //given
        String token = generateToken.generateToken("ala"); //User must exist in db.
        String imageName = "0Ay9Za7DxKIeTFIU0LEDJbMANnhDdl4pMonJun08110657CEST2020.jpg"; // image from db.

        //when
        MvcResult mvcResult = mockMvc.perform(get("/score?imageName=" + imageName)
                .contentType(MEDIA_TYPE)
                .header("authorization", "Bearer " + token))
                .andExpect(status().isOk())
                .andReturn();

        //then
        String score = mvcResult.getResponse().getHeader("actualScore");
        assertEquals(score, "1");
    }

    @Test
    public void getByTagMethodTets() throws Exception {
        //given
        String token = generateToken.generateToken("testy");

        //when
        MvcResult mvcResult = mockMvc.perform(get("/findByTag?tagEnum=TAG1")
                .contentType(MEDIA_TYPE)
                .header("authorization", "Bearer " + token))
                .andExpect(status().isOk())
                .andReturn();

        //then
        Type typeList = new TypeToken<ArrayList<ImageDto>>() {}.getType();
        ArrayList<ImageDto> images = new Gson().fromJson(mvcResult.getResponse().getContentAsString(), typeList);
        boolean result = images.stream().allMatch(image -> image.getTags().contains("TAG1"));
        assertTrue(result && images.size() == 2); // size == 2 becouse we got 2 images with TAG1 in ours db.
    }
}
