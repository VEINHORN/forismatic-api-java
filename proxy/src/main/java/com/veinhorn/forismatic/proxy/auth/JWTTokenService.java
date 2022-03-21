package com.veinhorn.forismatic.proxy.auth;

import io.jsonwebtoken.*;
import io.jsonwebtoken.impl.compression.GzipCompressionCodec;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.function.Supplier;

import static io.jsonwebtoken.impl.TextCodec.BASE64;

// TODO: Use TokenService from Spring Secure instead of custom one
@Service
public class JWTTokenService implements TokenService, Clock {
    private static final GzipCompressionCodec COMPRESSION_CODEC = new GzipCompressionCodec();

    private String issuer;
    private String secretKey;

    public JWTTokenService() {
        super();
        this.issuer = Objects.requireNonNull("forismatic");
        this.secretKey = BASE64.encode("www.forismatic.com");
    }

    @Override
    public String newToken(Map<String, String> attributes) {
        final Claims claims = Jwts.claims().setIssuer(issuer).setIssuedAt(new Date());
        claims.putAll(attributes);

        return Jwts
                .builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compressWith(COMPRESSION_CODEC)
                .compact();
    }

    @Override
    public Map<String, String> verify(String token) {
        final JwtParser parser = Jwts.parser().requireIssuer(issuer).setClock(this).setSigningKey(secretKey);
        return parseClaims(() -> parser.parseClaimsJws(token).getBody());
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
