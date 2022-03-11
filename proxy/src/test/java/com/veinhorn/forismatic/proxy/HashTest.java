package com.veinhorn.forismatic.proxy;

import org.apache.commons.codec.digest.DigestUtils;
import org.junit.jupiter.api.Test;

import java.security.NoSuchAlgorithmException;

public class HashTest {
    @Test
    public void testHash() throws NoSuchAlgorithmException {
        String hash = DigestUtils.sha1Hex("Если встать на голову, то небо будет под твоими ногами.");

        System.out.println(hash);
        System.out.println(hash.length());
    }
}
