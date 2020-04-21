package tests;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.zdjecia.services.RegularExpresions;
import org.zdjecia.services.imp.RegularExpresionsImp;

import static org.junit.Assert.*;

@SpringBootTest(classes = RegularExpresionsImp.class)
@RunWith(SpringRunner.class)
public class RegularExpresionsTest {

    @Autowired
    private RegularExpresions regularExpresions;

    @Test
    public void validUserNameTest(){
        //True
        assertTrue(regularExpresions.validUserName("ala"));
        assertTrue(regularExpresions.validUserName("kicaj123"));
        assertTrue(regularExpresions.validUserName("kamil999"));
        //False
        assertFalse(regularExpresions.validUserName("@ala"));
        assertFalse(regularExpresions.validUserName("al"));
        assertFalse(regularExpresions.validUserName("bon!ob"));
        assertFalse(regularExpresions.validUserName("QW_we_q"));
    }
}
