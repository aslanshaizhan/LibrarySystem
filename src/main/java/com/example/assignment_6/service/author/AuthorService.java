package com.example.assignment_6.service.author;

import com.example.assignment_6.model.dto.request.GetBookRequest;
import com.example.assignment_6.model.dto.request.LoginRequest;
import com.example.assignment_6.model.dto.request.RegisterRequest;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface AuthorService {
    ResponseEntity<?> register(RegisterRequest request);
    ResponseEntity<?> login(LoginRequest request);
    ResponseEntity<?> getOneBook(GetBookRequest request);
    ResponseEntity<?> deleteBook(Long id);
    ResponseEntity<?> allBooks(String fakeToken);
    ResponseEntity<?> deleteAccount(Long id);
    ResponseEntity<?> allAuthors();
}
