package com.veinhorn.forismatic.proxy.controller;

import com.veinhorn.forismatic.api.Forismatic;
import com.veinhorn.forismatic.api.Quote;
import com.veinhorn.forismatic.proxy.QuoteDto;
import com.veinhorn.forismatic.proxy.QuoteEntity;
import com.veinhorn.forismatic.proxy.QuoteRepository;
import com.veinhorn.forismatic.proxy.service.QuoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.Optional;
import java.util.function.Supplier;

@RestController
public class QuoteController {
    @Autowired
    private QuoteService service;

    @Autowired
    private QuoteRepository repository;

    @GetMapping("/quotes/random")
    public QuoteDto getRandomQuote() throws ResponseStatusException {
        return service
                .getRandomQuote()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "random entity not found"));

    }

    @GetMapping("/quotes/test")
    public Optional<Quote> test() {
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "random entity not found");
    }

    @GetMapping("/quotes/all")
    public Iterable<QuoteEntity> getAllQuotes() {
        return repository.findAll();
    }
}
