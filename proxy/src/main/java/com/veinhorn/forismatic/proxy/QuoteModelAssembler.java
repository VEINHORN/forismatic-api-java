package com.veinhorn.forismatic.proxy;

import com.veinhorn.forismatic.proxy.controller.QuoteController;
import com.veinhorn.forismatic.proxy.dto.QuoteDto;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class QuoteModelAssembler implements RepresentationModelAssembler<QuoteDto, EntityModel<QuoteDto>> {
    @Override
    public EntityModel<QuoteDto> toModel(QuoteDto quote) {
        return EntityModel.of(
                quote,
                linkTo(methodOn(QuoteController.class).getQuote(quote.getId())).withSelfRel()
        );
    }
}
