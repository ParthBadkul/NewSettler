package com.example.NewSettler.DTO;

import com.example.NewSettler.Enums.UserEnum;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class UserDto {

    @NotBlank(message = "Cant Be empty String")
    @Email(message = "Enter Valid Email")
    private String userName;

    @NotBlank(message = "Password Cant Be Null")
    private String password;

    private UserEnum role;

    public UserDto(String userName, String password, UserEnum role) {
        this.userName = userName;
        this.password = password;
        this.role = role;
    }

    public UserDto() {
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserEnum getRole() {
        return role;
    }

    public void setRole(UserEnum role) {
        this.role = role;
    }
}
