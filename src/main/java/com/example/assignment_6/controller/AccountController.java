package com.example.assignment_6.controller;

import com.example.assignment_6.model.dto.request.LoginRequest;
import com.example.assignment_6.model.dto.request.RegisterRequest;
import com.example.assignment_6.service.author.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountController {

    private final AuthorService authorService;

    @Autowired
    public AccountController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @PostMapping(value = "/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request){
        return authorService.register(request);
    }

    @PostMapping(value = "/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request){
        return authorService.login(request);
    }
}
