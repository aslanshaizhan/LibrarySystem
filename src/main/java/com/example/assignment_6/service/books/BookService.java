package com.example.assignment_6.service.books;

import com.example.assignment_6.model.dto.request.BookRequest;
import com.example.assignment_6.model.dto.request.UpdateBookRequest;
import org.springframework.http.ResponseEntity;

public interface BookService {
    ResponseEntity<?> addBook(BookRequest request);
    ResponseEntity<?> updateBook(UpdateBookRequest request);
}
