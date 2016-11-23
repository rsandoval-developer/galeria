package com.negociandola.cuponera.RestAPI.model;

/**
 * Created by ignacio on 10/07/16.
 */
public class UserResponse {
    private String id;
    private String token;

    public UserResponse(String id,String token){
        this.id = id;
        this.token = token;
    }

    public UserResponse(){

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
