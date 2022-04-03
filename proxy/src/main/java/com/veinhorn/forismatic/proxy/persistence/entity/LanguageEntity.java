package com.veinhorn.forismatic.proxy.persistence.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "LANGUAGE")
@Getter @Setter
@NoArgsConstructor
public class LanguageEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String code;

    private String name;

    public LanguageEntity(String code) {
        this.code = code;
    }
}
