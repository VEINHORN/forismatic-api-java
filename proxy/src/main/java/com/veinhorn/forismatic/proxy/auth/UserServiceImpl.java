package com.veinhorn.forismatic.proxy.auth;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private Map<String, User> users = new HashMap() {{
        put("Matt", new User("0", "matt", "idg"));
    }};

    @Override
    public User save(User user) {
        return users.put(user.getId(), user);
    }

    @Override
    public Optional<User> find(String id) {
        return Optional.ofNullable(users.get(id));
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return users
                .values()
                .stream()
                .filter(user -> Objects.equals(username, user.getUsername()))
                .findFirst();
    }
}
