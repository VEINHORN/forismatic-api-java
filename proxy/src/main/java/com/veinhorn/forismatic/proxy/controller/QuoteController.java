package com.veinhorn.forismatic.proxy.controller;

import com.veinhorn.forismatic.api.Forismatic;
import com.veinhorn.forismatic.api.Quote;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class QuoteController {
    @GetMapping("/quotes/random")
    public Quote getRandomQuote(@RequestParam(value = "name", defaultValue = "World") String name) throws IOException {
        Quote quote = new Forismatic().getQuote();
        return quote;
    }
}
