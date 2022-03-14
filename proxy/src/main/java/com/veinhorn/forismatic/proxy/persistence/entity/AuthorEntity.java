package com.veinhorn.forismatic.proxy.persistence.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "AUTHOR")
@Getter @Setter @NoArgsConstructor
public class AuthorEntity {
    private Integer id;
    private String name;
}
