package com.tiagoamp.gameoflife.model;

import java.util.Random;
import java.util.function.Supplier;

public class UniverseBuilder {

    public static Universe from(int[][] intSeed) {
        int length = intSeed.length;
        State[][] seed = new State[length][length];
        for (int r = 0; r < length; r++) {
            for (int c = 0; c < length; c++) {
                seed[r][c] = State.fromValue(intSeed[r][c]);
            }
        }
        return new Universe(seed);
    }

    public static Universe fromRandomState(int gridSize) {
        State[][] seed = new State[gridSize][gridSize];
        for (int r = 0; r < gridSize; r++) {
            for (int c = 0; c < gridSize; c++) {
                seed[r][c] = State.fromValue( eitherZeroOrOne.get() );
            }
        }
        return new Universe(seed);
    }


    private static Supplier<Integer> eitherZeroOrOne = () -> new Random().nextInt(2);

}
