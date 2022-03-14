package com.veinhorn.forismatic.proxy.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter @Setter
@RequiredArgsConstructor
public class QuoteDto {
    private final Integer id;
    private final String text;
    private final String author;
}
