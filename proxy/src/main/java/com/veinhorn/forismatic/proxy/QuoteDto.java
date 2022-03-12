package com.veinhorn.forismatic.proxy;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter @Setter
public class QuoteDto {
    private Integer id;
    private String text;
    private String link;
}
