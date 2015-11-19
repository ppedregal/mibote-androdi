package com.thinknowa.botin.sdk.model;

/**
 * Created by ppedregal on 17/11/15.
 */
public class Account {

    private String username;
    private String password;
    private String email;
    private String id;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

	@Override
	public String toString() {
		return "Account [username=" + username + ", password=" + password + ", email=" + email + ", id=" + id + "]";
	}
    
}
