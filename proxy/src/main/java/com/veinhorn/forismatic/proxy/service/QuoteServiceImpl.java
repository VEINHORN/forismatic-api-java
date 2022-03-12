package com.veinhorn.forismatic.proxy.service;

import com.veinhorn.forismatic.api.Forismatic;
import com.veinhorn.forismatic.api.Quote;
import com.veinhorn.forismatic.proxy.QuoteDto;
import com.veinhorn.forismatic.proxy.persistence.QuoteRepository;
import com.veinhorn.forismatic.proxy.exception.DuplicateQuoteException;
import com.veinhorn.forismatic.proxy.persistence.QuoteEntity;
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

            QuoteDto dto = new QuoteDto();
            dto.setId(saved.getId());
            dto.setText(saved.getText());

            return Optional.of(dto);
        } catch (DuplicateQuoteException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return Optional.empty();
    }

    private QuoteEntity save(Quote quote) throws DuplicateQuoteException {
        String hash = DigestUtils.sha1Hex(quote.getQuoteText());
        List<QuoteEntity> quotes = repository.findByHash(hash);

        // If hash of quote text does not exist in db, we save this new quote
        if (quotes.isEmpty()) {
            return repository.save(new QuoteEntity(quote.getQuoteText(), hash));
        } else if (quotes.size() == 1) {
            return quotes.get(0);
        }

        throw new DuplicateQuoteException();
    }
}
