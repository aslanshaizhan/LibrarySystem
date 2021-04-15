package com.example.assignment_6.model.dto.request;

import lombok.Data;

@Data
public class RegisterRequest {
    private String name;
    private String login;
    private String password;
}
