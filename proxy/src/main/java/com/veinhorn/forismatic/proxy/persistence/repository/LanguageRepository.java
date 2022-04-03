package com.veinhorn.forismatic.proxy.persistence.repository;

import com.veinhorn.forismatic.proxy.persistence.entity.LanguageEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LanguageRepository extends CrudRepository<LanguageEntity, Integer> {
    List<LanguageEntity> findByCode(String code);
}
