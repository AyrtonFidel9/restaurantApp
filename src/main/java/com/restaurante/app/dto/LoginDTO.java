package com.restaurante.app.dto;

import lombok.Data;

@Data
public class LoginDTO {

    private String usernameorEmail;
    private String password;
}
