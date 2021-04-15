package com.example.assignment_6.repository;

import com.example.assignment_6.model.entity.Books;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BooksRepo extends JpaRepository<Books , Long> {
}
