package com.example.assignment_6.controller;

import com.example.assignment_6.model.dto.request.UpdateBookRequest;
import com.example.assignment_6.service.author.AuthorService;
import com.example.assignment_6.service.books.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ManageSystemController {
    private final AuthorService authorService;
    private final BookService bookService;

    @Autowired
    public ManageSystemController(AuthorService authorService, BookService bookService) {
        this.authorService = authorService;
        this.bookService = bookService;
    }

    @PutMapping(value = "/edit")
    public ResponseEntity<?> edit(@RequestBody UpdateBookRequest request){
        return bookService.updateBook(request);
    }


}
