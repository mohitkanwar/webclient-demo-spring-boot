package com.mk.dummyserver.model;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public enum Genre {
    NON_FICTION, FANTASY, TECHNOLOGY, POLITICS, AUTOBIOGRAPHY, BIOGRAPHY, CHEATCODES, SOME;

    private static final List<Genre> VALUES =
            Collections.unmodifiableList(Arrays.asList(values()));
    private static final int SIZE = VALUES.size();
    private static final Random RANDOM = new Random();

    public static Genre randomGenre() {
        return VALUES.get(RANDOM.nextInt(SIZE));
    }
}
