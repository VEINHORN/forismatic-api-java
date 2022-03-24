package com.veinhorn.forismatic.proxy.auth;

import com.veinhorn.forismatic.proxy.auth.converter.ExtendedInformationConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.token.Token;
import org.springframework.security.core.token.TokenService;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Objects;
import java.util.Optional;

@Service
public class TokenAuthenticationService implements UserAuthenticationService {
    @Autowired
    private TokenService tokenService;

    @Autowired
    private UserService userService;

    @Override
    public Optional<String> login(String username, String password) {
        return userService
                .findByUsername(username)
                .filter(user -> Objects.equals(password, user.getPassword()))
                .map(user -> createToken(username).getKey());
    }

    @Override
    public Optional<User> findByToken(String token) {
        return Optional
                .of(tokenService.verifyToken(token))
                .map(UserToken::new)
                .map(UserToken::getUsername)
                .flatMap(userService::findByUsername);
    }

    @Override
    public void logout(User user) {}

    private Token createToken(String username) {
        return tokenService.allocateToken(
                new ExtendedInformationConverter(Collections.singletonMap(User.USERNAME, username)).convert()
        );
    }
}
