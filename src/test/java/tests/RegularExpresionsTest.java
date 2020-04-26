package tests;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.zdjecia.model.expresions.RegularExpresions;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@SpringBootTest(classes = RegularExpresions.class)
@RunWith(SpringRunner.class)
public class RegularExpresionsTest {


    @Test
    public void validUserNameTest(){
        //True
        assertTrue(RegularExpresions.validUserName("ala"));
        assertTrue(RegularExpresions.validUserName("kicaj123"));
        assertTrue(RegularExpresions.validUserName("kamil999"));
        //False
        assertFalse(RegularExpresions.validUserName("@ala"));
        assertFalse(RegularExpresions.validUserName("al"));
        assertFalse(RegularExpresions.validUserName("bon!ob"));
        assertFalse(RegularExpresions.validUserName("QW_we_q"));
    }
}
