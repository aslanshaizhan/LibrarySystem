package com.example.assignment_6.service.library;

import org.springframework.http.ResponseEntity;

public interface LibraryService {
    ResponseEntity<?> addLibrary(String name);
}
