package com.example.assignment_6.security.jwt;

import com.example.assignment_6.model.entity.Author;
import com.example.assignment_6.model.entity.Books;
import com.example.assignment_6.service.author.AuthorServiceImpl;
import com.example.assignment_6.service.books.BooksServiceImpl;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class TokenAuthentication implements Authentication {
    private String token;
    private Collection<? extends GrantedAuthority> authorities;
    private boolean isAuthenticated;
    private Author principal;

    public TokenAuthentication(String token) {
        this.token = token;
    }

    public TokenAuthentication(String token, Collection<? extends GrantedAuthority> authorities, boolean isAuthenticated,
                               Author principal) {
        this.token = token;
        this.authorities = authorities;
        this.isAuthenticated = isAuthenticated;
        this.principal = principal;
    }

    public TokenAuthentication(String token, boolean isAuthenticated,
                               Author principal) {
        this.token = token;
        this.authorities = principal.getAuthorities();
        this.isAuthenticated = isAuthenticated;
        this.principal = principal;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getAuthors() {
        return null;
    }

    @Override
    public String getName() {
        if (principal != null)
            return ((Author) principal).getName();
        else
            return null;
    }

    @Override
    public Object getPrincipal() {
        return principal;
    }

    @Override
    public boolean isAuthenticated() {
        return isAuthenticated;
    }

    @Override
    public void setAuthenticated(boolean value) throws IllegalArgumentException {
        isAuthenticated = value;
    }

    public String getToken() {
        return token;
    }

    public Author getAuthors() {
        AuthorServiceImpl authorService = (AuthorServiceImpl) this.principal;
        return authorService.getAuthors();
    }

}

