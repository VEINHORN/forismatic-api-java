package com.veinhorn.forismatic.proxy.auth;

import com.veinhorn.forismatic.proxy.auth.converter.ExtendedInformationConverter;
import io.jsonwebtoken.*;
import io.jsonwebtoken.impl.compression.GzipCompressionCodec;
import org.springframework.security.core.token.DefaultToken;
import org.springframework.security.core.token.Token;
import org.springframework.security.core.token.TokenService;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

import static io.jsonwebtoken.impl.TextCodec.BASE64;

@Service
public class JWTTokenService implements TokenService, Clock {
    private static final GzipCompressionCodec COMPRESSION_CODEC = new GzipCompressionCodec();
    private static final String ISSUER = "forismatic";
    private static final String SECRET_KEY = BASE64.encode("www.forismatic.com");

    @Override
    public Token allocateToken(String extendedInformation) {
        Date issuedAt = new Date();
        final Claims claims = Jwts.claims().setIssuer(ISSUER).setIssuedAt(issuedAt);
        claims.putAll(new ExtendedInformationConverter(extendedInformation).getExtendedInformation());

        String token = Jwts
                .builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compressWith(COMPRESSION_CODEC)
                .compact();

        return new DefaultToken(token, issuedAt.getTime(), extendedInformation);
    }

    @Override
    public Token verifyToken(String token) {
        final JwtParser parser = Jwts
                .parser()
                .requireIssuer(ISSUER)
                .setClock(this)
                .setSigningKey(SECRET_KEY);

        Map<String, String> claims = parseClaims(() -> parser.parseClaimsJws(token).getBody());
        int issuedAt = Integer.parseInt(claims.get(Claims.ISSUED_AT));

        return new DefaultToken(token, issuedAt, new ExtendedInformationConverter(claims).convert());
    }

    @Override
    public Date now() {
        return new Date();
    }

    private Map<String, String> parseClaims(Supplier<Claims> toClaims) {
        try {
            final Claims claims = toClaims.get();
            Map<String, String> result = new HashMap<>();

            for (Map.Entry<String, Object> e : claims.entrySet()) {
                result.put(e.getKey(), String.valueOf(e.getValue()));
            }

            return result;
        } catch (final IllegalArgumentException | JwtException e) {
            return Collections.emptyMap();
        }
    }
}
