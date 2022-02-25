package com.veinhorn.forismatic.api;

import java.util.Random;

public class Randomizer {
    private final static int MIN = 1;
    private final static int MAX = 99999;

    public String getRandom() {
        return "" + new Random().nextInt((MAX - MIN) + 1) + MIN;
    }
}
