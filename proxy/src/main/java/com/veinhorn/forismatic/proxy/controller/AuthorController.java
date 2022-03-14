package com.veinhorn.forismatic.proxy.controller;

import com.veinhorn.forismatic.proxy.dto.AuthorDto;
import com.veinhorn.forismatic.proxy.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AuthorController {
    @Autowired
    private AuthorService service;

    @GetMapping("/authors")
    public List<AuthorDto> getAllAuthors() {
        return service.getAllAuthors();
    }
}
