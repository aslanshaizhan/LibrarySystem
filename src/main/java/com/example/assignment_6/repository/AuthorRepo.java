package com.example.assignment_6.repository;

import com.example.assignment_6.model.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AuthorRepo extends JpaRepository<Author , Long> {
    boolean existsByLogin(String login);
    Author getByLogin(String  login);
    Author getByFakeToken(String fakeToken);
    Author findByBooks_Id(Long id);
}
