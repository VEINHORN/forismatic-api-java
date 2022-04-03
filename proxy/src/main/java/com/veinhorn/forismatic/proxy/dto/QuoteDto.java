package com.veinhorn.forismatic.proxy.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
@Builder
public class QuoteDto {
    private Integer id;
    private String text;
    private String author;

    private String forismaticQuoteLink;
}
