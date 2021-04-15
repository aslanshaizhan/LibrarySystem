package com.example.assignment_6.model.entity;

import com.example.assignment_6.model.dto.request.RegisterRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "authors")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "login")
    private String login;

    @Column(name = "password")
    private String password;

    @Column(name = "fake_token" , nullable = true)
    private String  fakeToken;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "author_books",
            joinColumns = @JoinColumn(
                    name = "author_id" , referencedColumnName = "id"
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "books_id" , referencedColumnName = "id"
            )
    )
    private List<Books> books;

    public Author(RegisterRequest request){
        this.name = request.getName();
        this.login = request.getLogin();
        this.password = request.getPassword();
    }


}
