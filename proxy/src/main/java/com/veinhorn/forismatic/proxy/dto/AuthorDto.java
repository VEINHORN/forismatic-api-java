package com.veinhorn.forismatic.proxy.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter @Setter
@RequiredArgsConstructor
public class AuthorDto {
    private final String name;
}
