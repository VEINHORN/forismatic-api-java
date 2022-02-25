package com.veinhorn.forismatic.api;

import java.util.Random;

/**
 * This class used for generation random key which affects quote selection on Forismatic API side
 */
public class Randomizer {
    private final static int MIN = 1;
    private final static int MAX = 99999;

    public String getRandom() {
        return "" + new Random().nextInt((MAX - MIN) + 1) + MIN;
    }
}
