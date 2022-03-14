package com.veinhorn.forismatic.proxy.service;

import com.veinhorn.forismatic.proxy.dto.QuoteDto;

import java.util.Optional;

public interface QuoteService {
    Optional<QuoteDto> getRandomQuote();
    Optional<QuoteDto> getQuote(Integer id);
}
