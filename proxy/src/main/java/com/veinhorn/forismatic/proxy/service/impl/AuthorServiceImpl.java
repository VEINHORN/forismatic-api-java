package com.veinhorn.forismatic.proxy.service.impl;

import com.veinhorn.forismatic.proxy.dto.AuthorDto;
import com.veinhorn.forismatic.proxy.persistence.repository.AuthorRepository;
import com.veinhorn.forismatic.proxy.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class AuthorServiceImpl implements AuthorService {
    @Autowired
    private AuthorRepository repository;

    @Override
    public List<AuthorDto> getAllAuthors() {
        return StreamSupport
                .stream(repository.findAll().spliterator(), false)
                .map(author -> new AuthorDto(author.getId(), author.getName()))
                .collect(Collectors.toList());
    }
}
