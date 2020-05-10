package tests;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.zdjecia.model.expresions.RegularExpresions;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class RegularExpresionsTest {


    @ParameterizedTest
    @CsvSource({
            "ala",
            "kicaj123",
            "kamil999",
            "kAsKa9999",
            "wojtek321",
            "kamilPL1997"
    })
    public void validUserNameShouldReturnTrue(String login) {
        assertTrue(RegularExpresions.validUserName(login));
    }

    @ParameterizedTest
    @CsvSource({
            "al",
            "b!un",
            "kamCio@",
            "a",
            "a@#",
            "!patryk",
            "?????",
    })
    public void validUserNameShouldReturnFalse(String login) {
        assertFalse(RegularExpresions.validUserName(login));
    }

    @ParameterizedTest
    @CsvSource({
            "simple@example.com",
            "very.common@example.com",
            "disposable.style.email.with+symbol@example.com",
            "other.email-with-hyphen@example.com",
            "fully-qualified-domain@example.com",
            "admin@mailserver1",
            "example@s.example",
    })
    public void validEmailShouldReturnTrue(String email) {
        assertTrue(RegularExpresions.validUserEmail(email));
    }

    @ParameterizedTest
    @CsvSource({
            "Abc.example.com",
            "A@b@c@example.com",
            "a\"b(c)d,e:f;g<h>i[j\\k]l@example.com",
            "just\"not\"right@example.com",
            "this is\"not\\allowed@example.com",
    })
    public void validEmailShouldReturnFalse(String email) {
        assertFalse(RegularExpresions.validUserEmail(email));
    }
}
