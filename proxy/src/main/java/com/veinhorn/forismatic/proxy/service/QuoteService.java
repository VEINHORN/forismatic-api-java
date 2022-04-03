package com.veinhorn.forismatic.proxy.service;

import com.veinhorn.forismatic.proxy.dto.QuoteDto;
import com.veinhorn.forismatic.proxy.exception.DuplicateQuoteException;

import java.util.Optional;

public interface QuoteService {
    Optional<QuoteDto> getRandomQuote();
    Optional<QuoteDto> findQuote(Integer id);
    QuoteDto saveQuote(QuoteDto quote) throws DuplicateQuoteException;
}
