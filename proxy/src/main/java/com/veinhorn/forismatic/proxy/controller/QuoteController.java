package com.veinhorn.forismatic.proxy.controller;

import com.veinhorn.forismatic.proxy.QuoteModelAssembler;
import com.veinhorn.forismatic.proxy.dto.QuoteDto;
import com.veinhorn.forismatic.proxy.exception.DuplicateQuoteException;
import com.veinhorn.forismatic.proxy.exception.QuoteNotFoundException;
import com.veinhorn.forismatic.proxy.persistence.entity.QuoteEntity;
import com.veinhorn.forismatic.proxy.persistence.repository.QuoteRepository;
import com.veinhorn.forismatic.proxy.service.QuoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/quotes")
public class QuoteController {
    @Autowired
    private QuoteService service;

    @Autowired
    private QuoteRepository repository;

    @Autowired
    private QuoteModelAssembler assembler;

    @GetMapping
    public Iterable<QuoteEntity> getAllQuotes() {
        return repository.findAll();
    }

    @GetMapping("/random")
    public QuoteDto getRandomQuote() throws ResponseStatusException {
        return service
                .getRandomQuote()
                .orElseThrow(QuoteNotFoundException::new);

    }

    @GetMapping("/{id}")
    public QuoteDto getQuote(@PathVariable Integer id) {
        return service
                .getQuote(id)
                .orElseThrow(QuoteNotFoundException::new);
    }

    @PostMapping
    public ResponseEntity<Object> createQuote(@RequestBody QuoteDto quote) throws DuplicateQuoteException {
            var entityModel = assembler.toModel(service.saveQuote(quote));
            return ResponseEntity
                    .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                    .body(entityModel);
    }
}
