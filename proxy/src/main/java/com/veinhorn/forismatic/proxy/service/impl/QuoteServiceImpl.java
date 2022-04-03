package com.veinhorn.forismatic.proxy.service.impl;

import com.veinhorn.forismatic.api.Forismatic;
import com.veinhorn.forismatic.api.Language;
import com.veinhorn.forismatic.api.Quote;
import com.veinhorn.forismatic.proxy.dto.QuoteDto;
import com.veinhorn.forismatic.proxy.exception.DuplicateQuoteException;
import com.veinhorn.forismatic.proxy.exception.UserNotFoundException;
import com.veinhorn.forismatic.proxy.persistence.entity.AuthorEntity;
import com.veinhorn.forismatic.proxy.persistence.entity.LanguageEntity;
import com.veinhorn.forismatic.proxy.persistence.entity.QuoteEntity;
import com.veinhorn.forismatic.proxy.persistence.entity.UserEntity;
import com.veinhorn.forismatic.proxy.persistence.repository.AuthorRepository;
import com.veinhorn.forismatic.proxy.persistence.repository.QuoteRepository;
import com.veinhorn.forismatic.proxy.persistence.repository.UserRepository;
import com.veinhorn.forismatic.proxy.service.LanguageService;
import com.veinhorn.forismatic.proxy.service.QuoteService;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class QuoteServiceImpl implements QuoteService {
    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private QuoteRepository repository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private LanguageService languageService;

    private Language getRandomLanguage() {
        return new Random().nextInt() % 2 == 0 ? Language.ENGLISH : Language.RUSSIAN;
    }

    @Override
    public Optional<QuoteDto> getRandomQuote() throws UserNotFoundException {
        try {
            // We use admin user for created_by when importing quote from Forismatic
            var adminUser = findUser(UserEntity.ADMIN_USERNAME).orElseThrow(UserNotFoundException::new);
            var randomLanguage = getRandomLanguage();
            var saved = save(toDto(
                    new Forismatic(randomLanguage).getQuote()),
                    adminUser,
                    Optional.of(randomLanguage)
            );

            return Optional.of(toDto(saved));
        } catch (DuplicateQuoteException e) {
            e.printStackTrace();
        } catch (IOException e) {
            // TODO: Case when we cannot get quote from Forismatic API
            e.printStackTrace();
        }

        return Optional.empty();
    }

    @Override
    public Optional<QuoteDto> findQuote(Integer id) {
        return repository.findById(id).map(this::toDto);
    }

    @Override
    public QuoteDto saveQuote(QuoteDto quote, Integer userId) throws UserNotFoundException, DuplicateQuoteException {
        return toDto(save(quote, userId));
    }

    private QuoteEntity save(QuoteDto quote, Integer userId) throws UserNotFoundException, DuplicateQuoteException {
        var found = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        return save(quote, found, Optional.empty());
    }

    private QuoteEntity save(QuoteDto quote, UserEntity user, Optional<Language> language) throws DuplicateQuoteException {
        var hash = hash(quote.getText());
        var quotes = repository.findByHash(hash);
        var lang = language
                .map(l -> languageService.findOrCreate(l.value()))
                .orElse(null);

        if (!quotes.isEmpty()) throw new DuplicateQuoteException();

        // If hash of quote text does not exist in database, we save author and quote
        return save(quote, hash, lang);
    }

    private QuoteEntity save(QuoteDto quote, String quoteHash, LanguageEntity language) {
        return repository.save(
                new QuoteEntity(
                        quote.getText(),
                        quoteHash,
                        quote.getAuthor(),
                        findOrCreateAuthor(Optional.ofNullable(quote.getAuthor())),
                        language
                )
        );
    }

    private Optional<UserEntity> findUser(String username) {
        return userRepository.findByUsername(username).stream().findFirst();
    }

    private AuthorEntity findOrCreateAuthor(Optional<String> authorName) {
        AuthorEntity author = null;

        if (authorName.isPresent()) {
            List<AuthorEntity> authors = authorRepository.findByName(authorName.get());
            if (authors.isEmpty()) {
                author = authorRepository.save(new AuthorEntity(authorName.get()));
            } else if (authors.size() == 1) {
                author = authors.get(0);
            }
            // TODO: What to do in case when there are more than 2 rows returned, probably need to add constraint
        } else {
            // TODO: Check that it returns at least 1 row
            author = authorRepository.findByName(AuthorEntity.UNKNOWN).get(0);
        }

        return author;
    }

    private String hash(String quoteText) {
        return DigestUtils.sha1Hex(quoteText);
    }

    private QuoteDto toDto(QuoteEntity quote) {
        return QuoteDto
                .builder()
                .id(quote.getId())
                .text(quote.getText())
                .author(quote.getAuthorName())
                .build();
    }

    private QuoteDto toDto(Quote quote) {
        return QuoteDto
                .builder()
                .text(quote.getQuoteText())
                .author(quote.getQuoteAuthor().orElse(null))
                .forismaticQuoteLink(quote.getQuoteLink().orElse(null))
                .build();
    }
}
