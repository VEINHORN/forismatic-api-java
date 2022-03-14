package com.veinhorn.forismatic.proxy.persistence.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "QUOTE")
@Getter @Setter @NoArgsConstructor
public class QuoteEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String text;

    private String hash;

    private String author;

    private AuthorEntity authorEntity;

    public QuoteEntity(String text, String hash, String author) {
        this.text = text;
        this.hash = hash;
        this.author = author;
    }
}
