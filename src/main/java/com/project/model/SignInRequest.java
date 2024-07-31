package com.project.model;

import lombok.Data;

@Data
public class SignInRequest {
    private String email;
    private String password;
    private String confirmPassword;
    private String name;
    private String surname;
}
