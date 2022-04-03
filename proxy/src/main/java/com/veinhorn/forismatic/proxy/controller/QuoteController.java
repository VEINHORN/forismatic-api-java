package com.veinhorn.forismatic.proxy.controller;

import com.veinhorn.forismatic.proxy.QuoteModelAssembler;
import com.veinhorn.forismatic.proxy.auth.User;
import com.veinhorn.forismatic.proxy.dto.QuoteDto;
import com.veinhorn.forismatic.proxy.exception.DuplicateQuoteException;
import com.veinhorn.forismatic.proxy.exception.QuoteNotFoundException;
import com.veinhorn.forismatic.proxy.exception.UserNotFoundException;
import com.veinhorn.forismatic.proxy.persistence.entity.QuoteEntity;
import com.veinhorn.forismatic.proxy.persistence.repository.QuoteRepository;
import com.veinhorn.forismatic.proxy.service.QuoteService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/quotes")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class QuoteController {
    private final @NonNull QuoteService service;
    private final @NonNull QuoteRepository repository;
    private final @NonNull QuoteModelAssembler assembler;

    @GetMapping
    public Iterable<QuoteEntity> getAllQuotes() {
        return repository.findAll();
    }

    @GetMapping("/random")
    public QuoteDto getRandomQuote() throws UserNotFoundException {
        return service
                .getRandomQuote()
                .orElseThrow(QuoteNotFoundException::new);
    }

    @GetMapping("/{id}")
    public QuoteDto getQuote(@PathVariable Integer id) {
        return service
                .findQuote(id)
                .orElseThrow(QuoteNotFoundException::new);
    }

    // TODO: Use unchecked exceptions instead, to simplify code
    @PostMapping
    public ResponseEntity<Object> createQuote(@RequestBody QuoteDto quote, Authentication auth) throws UserNotFoundException, DuplicateQuoteException {
            var userId = Integer.parseInt(((User) auth.getDetails()).getId());
            var entityModel = assembler.toModel(service.saveQuote(quote, userId));

            return ResponseEntity
                    .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                    .body(entityModel);
    }
}
