package com.veinhorn.forismatic.proxy.persistence.repository;

import com.veinhorn.forismatic.proxy.dto.AuthorDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class AuthorRepository {
    @Autowired
    private EntityManager entityManager;

    public List<AuthorDto> findAllAuthors() {
        return findAuthors()
                .stream()
                .map(AuthorDto::new)
                .collect(Collectors.toList());
    }

    private List<String> findAuthors() {
        String query = "SELECT author FROM QuoteEntity WHERE author IS NOT NULL GROUP BY author ORDER BY author";

        return (List<String>) entityManager.createQuery(query).getResultList();
    }
}
