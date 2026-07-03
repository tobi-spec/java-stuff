package org.example.springvalidation;

public class UserDto {

    @ValidStringLength(min = 2, max =5, message = "Username must be between 5 and 50 characters")
    private String username;


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

}
