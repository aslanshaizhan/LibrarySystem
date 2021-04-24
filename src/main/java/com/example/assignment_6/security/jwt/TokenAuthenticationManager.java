package com.example.assignment_6.security.jwt;

import com.example.assignment_6.model.entity.Author;
import com.example.assignment_6.model.entity.Books;
import com.example.assignment_6.service.author.AuthorService;
import com.example.assignment_6.service.books.BookService;
import io.jsonwebtoken.impl.DefaultClaims;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;
import spring.boot.endterm.exceptions.CustomAuthenticationException;
import spring.boot.endterm.service.token.TokenService;

@Service
public class TokenAuthenticationManager implements AuthenticationManager {
    private final AuthorService authorService;
    private final TokenService tokenService;


    public TokenAuthenticationManager(@Qualifier("userDetailsServiceImpl") AuthorService authorService, TokenService tokenService) {
        this.authorService = authorService;
        this.tokenService = tokenService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        return processAuthentication((TokenAuthentication) authentication);
    }

    public TokenAuthentication processAuthentication(TokenAuthentication authentication) throws AuthenticationException {
        String token = authentication.getToken();
        if (!tokenService.tokenValidation(token))
            throw new CustomAuthenticationException("Bad or expired token");
        return buildFullTokenAuthentication(authentication, token);
    }

    private TokenAuthentication buildFullTokenAuthentication(TokenAuthentication authentication, String token) {
        DefaultClaims claims = tokenService.getClaimsFromToken(token);
        Author author = authorService.loadUserByName(claims.get("name", String.class));
        if (!author.isAccountNonLocked()) {
            throw new CustomAuthenticationException("Author is blocked");
        }
        else if (!author.isEnabled()){
            throw new CustomAuthenticationException("Author is not activated");
        }
        else {
            return new TokenAuthentication(authentication.getToken(), true, author);
        }
    }

}

