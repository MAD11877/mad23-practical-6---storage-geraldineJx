package com.example.madpractical2;

import java.io.Serializable;

public class User implements Serializable {

    public String name;

    public String description;

    public Integer id;
    public boolean followed=false;
    public String password;

    public User() {}

    public User(String name, String description, Integer id, Boolean followed,String password) {
        this.name = name;
        this.description = description;
        this.id = id;
        this.followed = followed;
        this.password = password;
    }

    public String getName() {return name;}

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Boolean getFollowed() {
        return followed;
    }

    public void setFollowed(Boolean followed) {
        this.followed = followed;
    }
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
