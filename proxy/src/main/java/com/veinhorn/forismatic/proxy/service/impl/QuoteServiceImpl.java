package com.veinhorn.forismatic.proxy.service.impl;

import com.veinhorn.forismatic.api.Forismatic;
import com.veinhorn.forismatic.api.Quote;
import com.veinhorn.forismatic.proxy.dto.QuoteDto;
import com.veinhorn.forismatic.proxy.persistence.repository.QuoteRepository;
import com.veinhorn.forismatic.proxy.exception.DuplicateQuoteException;
import com.veinhorn.forismatic.proxy.persistence.entity.QuoteEntity;
import com.veinhorn.forismatic.proxy.service.QuoteService;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class QuoteServiceImpl implements QuoteService {
    @Autowired
    private QuoteRepository repository;

    @Override
    public Optional<QuoteDto> getRandomQuote() {
        try {
            QuoteEntity saved = save(new Forismatic().getQuote());

            QuoteDto dto = new QuoteDto(saved.getId(), saved.getText(), saved.getAuthorName());

            return Optional.of(dto);
        } catch (DuplicateQuoteException e) {
            e.printStackTrace();
        } catch (IOException e) {
            // TODO: Case when we cannot get quote from Forismatic API
            e.printStackTrace();
        }

        return Optional.empty();
    }

    @Override
    public Optional<QuoteDto> getQuote(Integer id) {
        return repository.findById(id).map(quote -> new QuoteDto(quote.getId(), quote.getText(), quote.getAuthorName()));
    }

    private QuoteEntity save(Quote quote) throws DuplicateQuoteException {
        String hash = DigestUtils.sha1Hex(quote.getQuoteText());
        List<QuoteEntity> quotes = repository.findByHash(hash);

        // If hash of quote text does not exist in db, we save this new quote
        if (quotes.isEmpty()) {
            return repository.save(new QuoteEntity(quote.getQuoteText(), hash, quote.getQuoteAuthor().orElse(null)));
        } else if (quotes.size() == 1) {
            QuoteEntity existing = quotes.get(0);
            // Update author of existing quote if it doesn't exist in database
            if (existing.getAuthorName() == null) quote.getQuoteAuthor().ifPresent(existing::setAuthorName);
            return quotes.get(0);
        }

        throw new DuplicateQuoteException();
    }
}
