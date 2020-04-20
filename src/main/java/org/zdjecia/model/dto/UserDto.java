package org.zdjecia.model.dto;


public class UserDto {
    private String userName;
    private String userPassword;

    public UserDto(String userName, String userPassword) {
        this.userName = userName;
        this.userPassword = userPassword;
    }

    public UserDto() {
    }

    public String getUserName() {
        return userName;
    }

    public String getUserPassword() {
        return userPassword;
    }
}
