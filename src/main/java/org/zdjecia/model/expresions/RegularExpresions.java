package org.zdjecia.model.expresions;

import java.util.regex.Pattern;

public interface RegularExpresions {
    static boolean validUserName(String userName) {
        Pattern pattern = Pattern.compile("[a-zA-Z0-9]{3,}");
        return pattern.matcher(userName).matches();
    }

    static boolean validUserPassword(String userPassword) {
        return true;
    }

    static boolean validUserEmail(String email){
        Pattern pattern = Pattern.compile("^[A-Za-z0-9+_.-]+@(.+)$");
        return pattern.matcher(email).matches();
    }
}
