package com.api.login.usuario.auth;

public enum UserRoler {
    
    ADMIN("admin"),
    USER("user");

    private String role;

    UserRoler(String role){
        this.role = role;
    }

    public String getRole(){
        return role;
    }
}
