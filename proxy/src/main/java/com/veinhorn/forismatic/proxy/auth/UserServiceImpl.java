package com.veinhorn.forismatic.proxy.auth;

import com.veinhorn.forismatic.proxy.persistence.entity.UserEntity;
import com.veinhorn.forismatic.proxy.persistence.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository repository;

    @Override
    public User save(User user) {
        UserEntity saved = repository.save(new UserEntity(user.getUsername(), user.getPassword()));
        return user;
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return repository
                .findByUsername(username)
                .stream()
                .findFirst()
                .map(user -> new User(user.getId().toString(), user.getUsername(), user.getPassword()));
    }
}
