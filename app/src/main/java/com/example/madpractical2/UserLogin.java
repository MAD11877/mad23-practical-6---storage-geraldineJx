package com.example.madpractical2;

public class UserLogin {

    public String username;

    public Long password;

    public UserLogin() {}

    public UserLogin(String username, Long password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getPassword() {
        return password;
    }

    public void setPassword(Long password) {
        this.password = password;
    }
}
