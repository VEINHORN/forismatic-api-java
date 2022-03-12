package com.veinhorn.forismatic.proxy.persistence;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "QUOTE")
@Getter @Setter @NoArgsConstructor
public class QuoteEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String text;

    private String hash;

    public QuoteEntity(String text, String hash) {
        this.text = text;
        this.hash = hash;
    }
}
