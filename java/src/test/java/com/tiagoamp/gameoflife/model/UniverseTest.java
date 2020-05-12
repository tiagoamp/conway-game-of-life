package com.tiagoamp.gameoflife.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static com.tiagoamp.gameoflife.model.State.DEAD;
import static com.tiagoamp.gameoflife.model.State.LIVE;
import static org.junit.jupiter.api.Assertions.*;

class UniverseTest {

    private Universe universe;

    @DisplayName("When next generation (sample 1)")
    @Test
    void testGeneration_sample1() {
        int[][] seed = {
                {1,0,1},
                {0,1,0},
                {1,0,1}
        };
        int[][] expected = {
                {0,0,0},
                {0,0,0},
                {0,0,0}
        };
        assertThatAreEquals(seed, expected);
    }

    @DisplayName("When next generation (sample 2)")
    @Test
    void testGeneration_sample2() {
        int[][] seed = {
                {1,1,1},
                {0,0,0},
                {0,0,0}
        };
        int[][] expected = {
                {1,1,1},
                {1,1,1},
                {1,1,1}
        };
        assertThatAreEquals(seed, expected);
    }

    @DisplayName("When next generation (sample 3)")
    @Test
    void testGeneration_sample3() {
        int[][] seed = {
                {0,0,0,0},
                {0,0,0,0},
                {1,1,1,0},
                {1,1,0,0}
        };
        int[][] expected = {
                {0,0,0,0},
                {0,1,0,0},
                {1,0,1,1},
                {1,0,1,1}
        };
        assertThatAreEquals(seed, expected);
    }

    private void assertThatAreEquals(int[][] seed, int[][] expected) {
        Universe universe = UniverseBuilder.from(seed);
        int[][] nextGeneration = universe.updateToNextGeneration();
        for (int i = 0; i < expected.length; i++) {
            for (int j = 0; j < expected.length; j++) {
                assertEquals(expected[i][j], nextGeneration[i][j],"should be equal for i  = " + i + " and j = " + j);
            }
        }
    }


    @DisplayName("NeighborFinder Tests")
    @Nested
    class NeighborFinderTest {

        private final int[][] grid = {
                {1,0,1},
                {0,1,0},
                {1,0,1}
        };

        private final Universe.NeighbourFinder finder = UniverseBuilder.from(grid).getFinder();

        @DisplayName("When neighbors state of top-left cell")
        @Test
        void getNeighboursStateFrom_topLeftCell() {
            State[] states = finder.getNeighboursStateFrom(0, 0);
            assertEquals(LIVE, states[0]); assertEquals(LIVE, states[1]); assertEquals(DEAD, states[2]);
            assertEquals(LIVE, states[3]);                                assertEquals(DEAD, states[4]);
            assertEquals(DEAD, states[5]); assertEquals(DEAD, states[6]); assertEquals(LIVE, states[7]);
        }

        @DisplayName("When neighbors state of top-center cell")
        @Test
        void getNeighboursStateFrom_topCenterCell() {
            State[] states = finder.getNeighboursStateFrom(0, 1);
            assertEquals(LIVE, states[0]); assertEquals(DEAD, states[1]); assertEquals(LIVE, states[2]);
            assertEquals(LIVE, states[3]);                                assertEquals(LIVE, states[4]);
            assertEquals(DEAD, states[5]); assertEquals(LIVE, states[6]); assertEquals(DEAD, states[7]);
        }

        @DisplayName("When neighbors state of top-right cell")
        @Test
        void getNeighboursStateFrom_topRightCell() {
            State[] states = finder.getNeighboursStateFrom(0, 2);
            assertEquals(DEAD, states[0]); assertEquals(LIVE, states[1]); assertEquals(LIVE, states[2]);
            assertEquals(DEAD, states[3]);                                assertEquals(LIVE, states[4]);
            assertEquals(LIVE, states[5]); assertEquals(DEAD, states[6]); assertEquals(DEAD, states[7]);
        }

        @DisplayName("When neighbors state of middle-left cell")
        @Test
        void getNeighboursStateFrom_middleLeftCell() {
            State[] states = finder.getNeighboursStateFrom(1, 0);
            assertEquals(LIVE, states[0]); assertEquals(LIVE, states[1]); assertEquals(DEAD, states[2]);
            assertEquals(DEAD, states[3]);                                assertEquals(LIVE, states[4]);
            assertEquals(LIVE, states[5]); assertEquals(LIVE, states[6]); assertEquals(DEAD, states[7]);
        }

        @DisplayName("When neighbors state of middle cell")
        @Test
        void getNeighboursStateFrom_middleCell() {
            State[] states = finder.getNeighboursStateFrom(1, 1);
            assertEquals(LIVE, states[0]); assertEquals(DEAD, states[1]); assertEquals(LIVE, states[2]);
            assertEquals(DEAD, states[3]);                                assertEquals(DEAD, states[4]);
            assertEquals(LIVE, states[5]); assertEquals(DEAD, states[6]); assertEquals(LIVE, states[7]);
        }

        @DisplayName("When neighbors state of middle-right cell")
        @Test
        void getNeighboursStateFrom_middleRightCell() {
            State[] states = finder.getNeighboursStateFrom(1, 2);
            assertEquals(DEAD, states[0]); assertEquals(LIVE, states[1]); assertEquals(LIVE, states[2]);
            assertEquals(LIVE, states[3]);                                assertEquals(DEAD, states[4]);
            assertEquals(DEAD, states[5]); assertEquals(LIVE, states[6]); assertEquals(LIVE, states[7]);
        }

        @DisplayName("When neighbors state of bottom-left cell")
        @Test
        void getNeighboursStateFrom_bottomLeftCell() {
            State[] states = finder.getNeighboursStateFrom(2, 0);
            assertEquals(DEAD, states[0]); assertEquals(DEAD, states[1]); assertEquals(LIVE, states[2]);
            assertEquals(LIVE, states[3]);                                assertEquals(DEAD, states[4]);
            assertEquals(LIVE, states[5]); assertEquals(LIVE, states[6]); assertEquals(DEAD, states[7]);
        }

        @DisplayName("When neighbors state of bottom-center cell")
        @Test
        void getNeighboursStateFrom_bottomCenterCell() {
            State[] states = finder.getNeighboursStateFrom(2, 1);
            assertEquals(DEAD, states[0]); assertEquals(LIVE, states[1]); assertEquals(DEAD, states[2]);
            assertEquals(LIVE, states[3]);                                assertEquals(LIVE, states[4]);
            assertEquals(LIVE, states[5]); assertEquals(DEAD, states[6]); assertEquals(LIVE, states[7]);
        }

        @DisplayName("When neighbors state of bottom-right cell")
        @Test
        void getNeighboursStateFrom_bottomRightCell() {
            State[] states = finder.getNeighboursStateFrom(2, 2);
            assertEquals(LIVE, states[0]); assertEquals(DEAD, states[1]); assertEquals(DEAD, states[2]);
            assertEquals(DEAD, states[3]);                                assertEquals(LIVE, states[4]);
            assertEquals(DEAD, states[5]); assertEquals(LIVE, states[6]); assertEquals(LIVE, states[7]);
        }

    }

}