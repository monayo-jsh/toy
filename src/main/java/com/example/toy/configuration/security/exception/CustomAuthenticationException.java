package com.example.toy.configuration.security.exception;

import org.springframework.security.core.AuthenticationException;

public class CustomAuthenticationException extends AuthenticationException {

    public CustomAuthenticationException(Throwable t) {
        super(t.getMessage(), t);
    }
    
}
