package com.veinhorn.forismatic.proxy.persistence.repository;

import com.veinhorn.forismatic.proxy.persistence.entity.QuoteEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuoteRepository extends CrudRepository<QuoteEntity, Integer> {
    List<QuoteEntity> findByHash(String hash);
}
