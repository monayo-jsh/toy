package com.example.toy.configuration.security;

import com.example.toy.configuration.security.exception.CustomAuthenticationException;
import com.example.toy.database.entity.UserEntity;
import com.example.toy.database.repository.UserRepository;
import com.example.toy.domain.ResultCode;
import com.example.toy.restfull.controllers.exception.BadRequestException;
import com.example.toy.restfull.controllers.exception.NotFoundException;
import com.example.toy.restfull.controllers.exception.UnauthorizedException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.ObjectUtils;

public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Autowired private PasswordEncoder passwordEncoder;
    @Autowired private UserRepository userRepository;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        if (ObjectUtils.isEmpty(authentication.getName())) {
            throw new CustomAuthenticationException(new BadRequestException(ResultCode.INVALID_PARAM, "email is not empty"));
        }

        if(ObjectUtils.isEmpty(authentication.getCredentials())) {
            throw new CustomAuthenticationException(new BadRequestException(ResultCode.INVALID_PARAM, "password is not empty"));
        }

        UserEntity user = userRepository.findById(authentication.getName())
                                              .orElseThrow(() -> new CustomAuthenticationException(new NotFoundException()));

        if (!passwordEncoder.matches((String) authentication.getCredentials(), user.getPassword())) {
            throw new CustomAuthenticationException(new UnauthorizedException("password is different"));
        }

        List<GrantedAuthority> roles = List.of(new SimpleGrantedAuthority("USER"));

        return new UsernamePasswordAuthenticationToken(user, null, roles);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
