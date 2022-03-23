package com.veinhorn.forismatic.proxy.auth;

import java.util.Optional;

public interface UserService {
    User save(User user);
    Optional<User> findByUsername(String username);
}
