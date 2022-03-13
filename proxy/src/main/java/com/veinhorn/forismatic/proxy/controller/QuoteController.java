package com.veinhorn.forismatic.proxy.controller;

import com.veinhorn.forismatic.proxy.QuoteDto;
import com.veinhorn.forismatic.proxy.exception.QuoteNotFoundException;
import com.veinhorn.forismatic.proxy.persistence.QuoteEntity;
import com.veinhorn.forismatic.proxy.persistence.QuoteRepository;
import com.veinhorn.forismatic.proxy.service.QuoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

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
                .orElseThrow(QuoteNotFoundException::new);

    }

    @GetMapping("/quotes/{id}")
    public QuoteDto getQuote(@PathVariable Integer id) {
        return service
                .getQuote(id)
                .orElseThrow(QuoteNotFoundException::new);
    }

    @GetMapping("/quotes/all")
    public Iterable<QuoteEntity> getAllQuotes() {
        return repository.findAll();
    }
}
