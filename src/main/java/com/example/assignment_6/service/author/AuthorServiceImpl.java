package com.example.assignment_6.service.author;

import com.example.assignment_6.model.dto.request.GetBookRequest;
import com.example.assignment_6.model.dto.request.LoginRequest;
import com.example.assignment_6.model.dto.request.RegisterRequest;
import com.example.assignment_6.model.dto.response.AuthorResponse;
import com.example.assignment_6.model.entity.Author;
import com.example.assignment_6.model.entity.Books;
import com.example.assignment_6.repository.AuthorRepo;
import com.example.assignment_6.repository.BooksRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepo authorRepo;
    private final BooksRepo booksRepo;

    @Autowired
    public AuthorServiceImpl(AuthorRepo authorRepo, BooksRepo booksRepo) {
        this.authorRepo = authorRepo;
        this.booksRepo = booksRepo;
    }

    @Override
    public ResponseEntity<?> register(RegisterRequest request) {
        boolean existsByLogin = authorRepo.existsByLogin(request.getLogin());
        if (existsByLogin){
            throw new ResponseStatusException(HttpStatus.CONFLICT , "User with this login : " + request.getLogin() + " is alreadu exists");
        }
        Author author = new Author(request);
        authorRepo.save(author);
        return new ResponseEntity<>("Successfully registered" , HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> login(LoginRequest request) {
        Author author = authorRepo.getByLogin(request.getLogin());
        if (author==null){
            throw new ResponseStatusException(HttpStatus.CONFLICT , "This user does not exist");
        }
        if (!request.getPassword().equals(author.getPassword())){
            throw new ResponseStatusException(HttpStatus.CONFLICT , "Check password");
        }
        Random rand =  new Random();
        int fakeToken = rand.nextInt((999999 - 100000) + 1) + 100000;
        String asd = Integer.toString(fakeToken);
        author.setFakeToken(asd);
        authorRepo.save(author);
        return new ResponseEntity<>("Your token is " + fakeToken , HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> getOneBook(GetBookRequest request) {
        Author author = authorRepo.getByFakeToken(request.getFakeToken());
        if (author==null){
            throw new ResponseStatusException(HttpStatus.CONFLICT , "failed");
        }
        Books books = booksRepo.getOne(request.getId());
        System.err.println(request.getId());
        System.err.println(books);
        if (books==null){
            throw new ResponseStatusException(HttpStatus.CONFLICT , "Failed");
        }
        List<Books> books1 = new ArrayList<>();
        books1.add(books);
        author.setBooks(books1);
        authorRepo.save(author);
        return new ResponseEntity<>(books  , HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> deleteBook(Long id) {
        Books books = booksRepo.getOne(id);
        if (books==null){
            throw new ResponseStatusException(HttpStatus.CONFLICT , "Not found");
        }
        Author author = authorRepo.findByBooks_Id(id);
        author.setBooks(null);
        authorRepo.save(author);
        booksRepo.delete(books);
        return new ResponseEntity<>("Deleted" , HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> allBooks(String fakeToken) {
        Author author = authorRepo.getByFakeToken(fakeToken);
        if (author==null){
            throw new ResponseStatusException(HttpStatus.CONFLICT , "Failed");
        }
        List<Books> allBooks = author.getBooks();
        return new ResponseEntity<>(allBooks , HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> deleteAccount(Long id) {
        Author author = authorRepo.getOne(id);
        if (author==null){
            throw new ResponseStatusException(HttpStatus.CONFLICT , "Not exists");
        }
        author.setBooks(null);
        authorRepo.save(author);
        authorRepo.delete(author);
        return new ResponseEntity<>("Account deleted" , HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> allAuthors() {
        List<Author> authors = authorRepo.findAll();
        List<AuthorResponse> response = new ArrayList<>();
        for (Author a : authors){
            response.add(new AuthorResponse(a.getName() , a.getLogin()));
        }
        return new ResponseEntity<>(response , HttpStatus.OK);
    }
}
