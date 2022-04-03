package com.veinhorn.forismatic.proxy.service;

import com.veinhorn.forismatic.proxy.persistence.entity.LanguageEntity;

public interface LanguageService {
    // TODO: Implement detect language method

    /**
     * Returns language that already exists or creates it and then returns
     * @param languageCode is like ru, en
     * @return
     */
    LanguageEntity findOrCreate(String languageCode);
}
