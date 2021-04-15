package com.example.assignment_6.controller;

import com.example.assignment_6.model.dto.request.GetBookRequest;
import com.example.assignment_6.model.dto.request.UpdateBookRequest;
import com.example.assignment_6.service.author.AuthorService;
import com.example.assignment_6.service.books.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class SystemController {
    private final AuthorService authorService;
    private final BookService bookService;

    @Autowired
    public SystemController(AuthorService authorService, BookService bookService) {
        this.authorService = authorService;
        this.bookService = bookService;
    }

    @PostMapping(value = "/get/books")
    public ResponseEntity<?> getBooks(@RequestBody GetBookRequest request){
        return authorService.getOneBook(request);
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        return authorService.deleteBook(id);
    }

    @GetMapping(value = "/get/user/books")
    public ResponseEntity<?> allBooks(@RequestParam(name = "token")String token){
        return authorService.allBooks(token);
    }

    @PatchMapping(value = "/update/fields")
    public ResponseEntity<?> updateFields(@RequestBody UpdateBookRequest request){
        return bookService.updateBook(request);
    }

    @DeleteMapping(value = "/delete/account/{id}")
    public ResponseEntity<?> deleteAccount(@PathVariable Long id){
        return authorService.deleteAccount(id);
    }

    @GetMapping(value = "/all/users")
    public ResponseEntity<?> allUsers(){
        return authorService.allAuthors();
    }
}
