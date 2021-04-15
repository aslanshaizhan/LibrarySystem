package com.example.assignment_6.model.entity;

import jdk.jfr.Enabled;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "libraries")
@AllArgsConstructor
@NoArgsConstructor
public class Library {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String  name;

    public Library(String name) {
        this.name = name;
    }
}
