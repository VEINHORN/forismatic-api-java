package com.veinhorn.forismatic.proxy.service.impl;

import com.veinhorn.forismatic.proxy.dto.AuthorDto;
import com.veinhorn.forismatic.proxy.persistence.repository.AuthorRepositoryExtended;
import com.veinhorn.forismatic.proxy.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService {
    @Autowired
    private AuthorRepositoryExtended repository;

    @Override
    public List<AuthorDto> getAllAuthors() {
        return repository.findAllAuthors();
    }
}
