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

    @Column(name = "author")
    private String authorName;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "author_id", referencedColumnName = "id")
    private AuthorEntity author;

    public QuoteEntity(String text, String hash, String authorName, AuthorEntity author) {
        this.text = text;
        this.hash = hash;
        this.authorName = authorName;
        this.author = author;
    }
}
