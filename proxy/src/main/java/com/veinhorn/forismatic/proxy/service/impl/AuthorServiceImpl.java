package com.veinhorn.forismatic.proxy.service.impl;

import com.veinhorn.forismatic.proxy.dto.AuthorDto;
import com.veinhorn.forismatic.proxy.persistence.repository.AuthorRepository;
import com.veinhorn.forismatic.proxy.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class AuthorServiceImpl implements AuthorService {
    @Autowired
    private AuthorRepository repository;

    @Override
    public Page<AuthorDto> getAllAuthors(Pageable pageable) {
        return repository.findAll(pageable)
                .map(author -> new AuthorDto(author.getId(), author.getName()));
    }
}
