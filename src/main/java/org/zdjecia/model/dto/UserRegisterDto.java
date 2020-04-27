package org.zdjecia.model.dto;


public class UserRegisterDto extends UserDto{
    private String email;

    public UserRegisterDto(String userName, String userPassword, String email) {
        super(userName, userPassword);
        this.email = email;
    }

    public UserRegisterDto() {
    }

    public String getEmail() {
        return email;
    }
}
