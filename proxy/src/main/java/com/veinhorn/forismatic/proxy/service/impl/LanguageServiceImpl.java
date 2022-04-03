package com.veinhorn.forismatic.proxy.service.impl;

import com.veinhorn.forismatic.proxy.persistence.entity.LanguageEntity;
import com.veinhorn.forismatic.proxy.persistence.repository.LanguageRepository;
import com.veinhorn.forismatic.proxy.service.LanguageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LanguageServiceImpl implements LanguageService {
    @Autowired private LanguageRepository repository;

    @Override
    public LanguageEntity findOrCreate(String languageCode) {
        return repository
                .findByCode(languageCode)
                .stream()
                .findFirst()
                .orElse(repository.save(new LanguageEntity(languageCode)));
    }
}
