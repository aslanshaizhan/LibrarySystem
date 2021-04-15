package com.example.assignment_6.service.library;

import com.example.assignment_6.model.entity.Library;
import com.example.assignment_6.repository.LibraryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class LibraryServiceImpl implements LibraryService{

    private final LibraryRepo libraryRepo;

    @Autowired
    public LibraryServiceImpl(LibraryRepo libraryRepo) {
        this.libraryRepo = libraryRepo;
    }

    @Override
    public ResponseEntity<?> addLibrary(String name) {
        boolean existByName = libraryRepo.existsByName(name);
        if (existByName){
            throw new ResponseStatusException(HttpStatus.CONFLICT , "This library is already exists" );
        }
        Library library = new Library(name);
        libraryRepo.save(library);
        return new ResponseEntity<>("Successfully added" , HttpStatus.OK);
    }
}
