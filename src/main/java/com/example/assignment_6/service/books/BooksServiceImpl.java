package com.example.assignment_6.service.books;

import com.example.assignment_6.model.dto.request.BookRequest;
import com.example.assignment_6.model.dto.request.UpdateBookRequest;
import com.example.assignment_6.model.entity.Books;
import com.example.assignment_6.model.entity.Library;
import com.example.assignment_6.repository.BooksRepo;
import com.example.assignment_6.repository.LibraryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class BooksServiceImpl implements BookService{

    private final BooksRepo booksRepo;
    private final LibraryRepo libraryRepo;

    @Autowired
    public BooksServiceImpl(BooksRepo booksRepo, LibraryRepo libraryRepo) {
        this.booksRepo = booksRepo;
        this.libraryRepo = libraryRepo;
    }

    @Override
    public ResponseEntity<?> addBook(BookRequest request) {
        Library library = libraryRepo.getOne(request.getLibraryId());
        if (library==null){
            throw new ResponseStatusException(HttpStatus.CONFLICT , "This library does not exist");
        }
        Books books = new Books(request , library);
        booksRepo.save(books);
        return new ResponseEntity<>("Success"  , HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> updateBook(UpdateBookRequest request) {
        Books books = booksRepo.getOne(request.getId());
        if (books==null){
            throw new ResponseStatusException(HttpStatus.CONFLICT , "Failed");
        }
        books.setName(request.getName());
        books.setDescription(request.getDescription());
        booksRepo.save(books);
        return new ResponseEntity<>("Success" , HttpStatus.OK);
    }
}
