package com.thinknowa.botin.sdk.model;

/**
 * Created by ppedregal on 17/11/15.
 */
public class Login {

    private String email;
    private String password;

    public Login(String email, String password){
        this.email =email;
        this.password=password;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

}
