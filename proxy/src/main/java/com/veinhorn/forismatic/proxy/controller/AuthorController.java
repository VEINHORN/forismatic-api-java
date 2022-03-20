package com.veinhorn.forismatic.proxy.controller;

import com.veinhorn.forismatic.proxy.dto.AuthorDto;
import com.veinhorn.forismatic.proxy.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthorController {
    @Autowired
    private AuthorService service;

    @GetMapping("/authors")
    public Page<AuthorDto> getAllAuthors(@RequestParam(name = "page", defaultValue = "0") Integer page,
                                         @RequestParam(name = "size", defaultValue = "5") Integer size) {
        return service.getAllAuthors(PageRequest.of(page, size).withSort(Sort.by("name")));
    }
}
