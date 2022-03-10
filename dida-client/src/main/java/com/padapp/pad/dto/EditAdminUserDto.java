package com.padapp.pad.dto;



import javax.validation.constraints.NotBlank;

public class EditAdminUserDto {

    private String id;

    @NotBlank(message = "username is null")
    private String username;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
