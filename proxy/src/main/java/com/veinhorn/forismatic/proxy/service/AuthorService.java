package com.veinhorn.forismatic.proxy.service;

import com.veinhorn.forismatic.proxy.dto.AuthorDto;

import java.util.List;

public interface AuthorService {
    List<AuthorDto> getAllAuthors();
}
