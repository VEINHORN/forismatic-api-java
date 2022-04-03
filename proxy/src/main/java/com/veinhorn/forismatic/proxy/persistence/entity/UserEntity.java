package com.veinhorn.forismatic.proxy.persistence.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(schema = "public", name = "USER")
@Getter @Setter @NoArgsConstructor
public class UserEntity {
    public static final String ADMIN_USERNAME = "admin";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String username;

    private String password;

    public UserEntity(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
