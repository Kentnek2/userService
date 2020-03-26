package com.example.userService.model;

import java.io.Serializable;

public class UserUI implements Serializable {

    private long id;
    private String login;
    private String password;

    public  UserUI(){

    }

    public UserUI(long id, String login, String password){
        this.id = id;
        this.login = login;
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
