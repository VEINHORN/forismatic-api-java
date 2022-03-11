package com.veinhorn.forismatic.proxy.service;

import com.veinhorn.forismatic.api.Forismatic;
import com.veinhorn.forismatic.api.Quote;
import com.veinhorn.forismatic.proxy.QuoteDto;
import com.veinhorn.forismatic.proxy.QuoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Optional;

@Service
public class QuoteServiceImpl implements QuoteService {
    @Autowired
    private QuoteRepository repository;

    @Override
    public Optional<QuoteDto> getRandomQuote() {
        // Optional<QuoteDto> randomQuote = Optional.empty();
        try {
            Quote quote = new Forismatic().getQuote();


            QuoteDto dto = new QuoteDto();
            dto.setText(quote.getQuoteText());
            quote.getQuoteLink().ifPresent(dto::setLink);

            return Optional.of(dto);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return Optional.empty();
    }
}
