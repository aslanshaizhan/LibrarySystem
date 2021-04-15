package com.example.assignment_6.model.entity;

import com.example.assignment_6.model.dto.request.BookRequest;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "books")
@Getter
@Setter
@Proxy(lazy = false)
public class Books {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "decription" , columnDefinition = "TEXT")
    private String description;

    @ManyToOne
    private Library library;

    @JsonIgnore
    @ManyToMany(mappedBy = "books" , fetch = FetchType.EAGER)
    private List<Author> authors;

    public Books(BookRequest request , Library library) {
        this.name = request.getName();
        this.description = request.getDescription();
        this.library = library;
    }

    public Books() {
    }

}
