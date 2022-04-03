package com.veinhorn.forismatic.proxy.service;

import com.veinhorn.forismatic.proxy.dto.QuoteDto;
import com.veinhorn.forismatic.proxy.exception.DuplicateQuoteException;
import com.veinhorn.forismatic.proxy.exception.UserNotFoundException;

import java.util.Optional;

public interface QuoteService {
    Optional<QuoteDto> getRandomQuote() throws UserNotFoundException;
    Optional<QuoteDto> findQuote(Integer id);
    QuoteDto saveQuote(QuoteDto quote, Integer userId) throws UserNotFoundException, DuplicateQuoteException;
}
