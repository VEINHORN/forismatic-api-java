package com.veinhorn.forismatic.api;

import org.junit.Test;

import static org.junit.Assert.*;

public class RandomizerTest {
    @Test
    public void testRandomizer() {
        for (int i = 0; i < 100; i++) {
            String random = new Randomizer().getRandom();
            assertTrue(random.length() >= 1 && random.length() <= 6);
        }
    }
}