package com.example.assignment_6.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

@Data
@AllArgsConstructor
public class AuthorResponse {
    private String name;
    private String login;
}
