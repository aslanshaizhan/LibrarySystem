package com.example.assignment_6.repository;

import com.example.assignment_6.model.entity.Library;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LibraryRepo extends JpaRepository<Library , Long> {
    boolean existsByName(String name);
}
