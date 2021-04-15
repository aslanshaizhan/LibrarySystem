package com.example.assignment_6.controller;

import com.example.assignment_6.model.dto.request.BookRequest;
import com.example.assignment_6.service.books.BookService;
import com.example.assignment_6.service.library.LibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class ManageController {

    private final LibraryService libraryService;
    private final BookService bookService;

    @Autowired
    public ManageController(LibraryService libraryService, BookService bookService) {
        this.libraryService = libraryService;
        this.bookService = bookService;
    }

    @PostMapping(value = "/new/library")
    public ResponseEntity<?> addLibrary(@RequestBody Map<String  , String> name){
        return libraryService.addLibrary(name.get("name"));
    }

    @PostMapping(value = "/new/book")
    public ResponseEntity<?> addBook(@RequestBody BookRequest request){
        return bookService.addBook(request);
    }
}
