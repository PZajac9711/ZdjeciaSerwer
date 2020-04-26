package org.zdjecia.services.imp;

import org.springframework.stereotype.Component;
import org.zdjecia.services.RegularExpresions;

import java.util.regex.Pattern;

//To chyba powinno byc w model > regularExpression ???
@Component(value = "regularExpresions")
public class RegularExpresionsImp implements RegularExpresions {

    @Override
    public boolean validUserName(String userName) {
        Pattern pattern = Pattern.compile("[a-zA-Z0-9]{3,}");
        return pattern.matcher(userName).matches();
    }

    //DODAC POLITYKE HASEL
    @Override
    public boolean validUserPassword(String userPassword) {
        return true;
    }
}
