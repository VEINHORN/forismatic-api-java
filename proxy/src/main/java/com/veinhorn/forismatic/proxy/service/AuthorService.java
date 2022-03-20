package com.veinhorn.forismatic.proxy.service;

import com.veinhorn.forismatic.proxy.dto.AuthorDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AuthorService {
    Page<AuthorDto> getAllAuthors(Pageable pageable);
}
