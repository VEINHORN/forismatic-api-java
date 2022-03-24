package com.veinhorn.forismatic.proxy.auth;

import com.veinhorn.forismatic.proxy.auth.converter.ExtendedInformationConverter;
import lombok.AllArgsConstructor;
import org.springframework.security.core.token.Token;

@AllArgsConstructor
public class UserToken {
    private final Token token;

    public String getUsername() {
        return new ExtendedInformationConverter(token.getExtendedInformation())
                .getExtendedInformation()
                .get(User.USERNAME);
    }
}
