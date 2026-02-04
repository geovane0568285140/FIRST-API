package com.frota.project.model;

public enum UserRole {

    ADMIN("ADMIN"),

    USER("USER"),

    MODERATOR("MODERATOR");

    private String role;

    UserRole(String role){
        this.role = role;
    }

    public String getRole(){
        return role;
    }

}
