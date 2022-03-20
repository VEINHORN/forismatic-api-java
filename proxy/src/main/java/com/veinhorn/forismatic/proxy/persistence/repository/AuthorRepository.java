package com.veinhorn.forismatic.proxy.persistence.repository;

import com.veinhorn.forismatic.proxy.persistence.entity.AuthorEntity;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorRepository extends PagingAndSortingRepository<AuthorEntity, Integer> {
    List<AuthorEntity> findByName(String name);
}
