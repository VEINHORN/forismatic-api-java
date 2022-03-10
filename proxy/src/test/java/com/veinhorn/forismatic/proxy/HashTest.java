package com.veinhorn.forismatic.proxy;

import org.apache.commons.codec.digest.DigestUtils;
import org.junit.jupiter.api.Test;

import java.security.NoSuchAlgorithmException;

public class HashTest {
    @Test
    public void testHash() throws NoSuchAlgorithmException {
        String hash = DigestUtils.sha1Hex("test");

        System.out.println(hash);
        System.out.println(hash.length());
    }
}
