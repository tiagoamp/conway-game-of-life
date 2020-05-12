package com.tiagoamp.gameoflife.model;

import java.util.Arrays;

import static com.tiagoamp.gameoflife.model.State.LIVE;
import static com.tiagoamp.gameoflife.rules.Rules.anyDeadCellWithThreeLiveNeighborsBecomesLiveCell;
import static com.tiagoamp.gameoflife.rules.Rules.anyLiveCellWithTwoOrThreeLiveNeighborsSurvives;

/*
 * The universe of the Game of Life is an infinite, two-dimensional orthogonal grid of square cells.
 */
public class Universe {

    private State[][] grid;
    private final int gridSize;
    private final NeighbourFinder finder;


    public Universe(final State[][] seed) {
        grid = seed;
        gridSize = grid.length;
        finder = new NeighbourFinder();
    }


    public int[][] updateToNextGeneration() {
        grid = calculateNextGeneration();
        return getBinaryGrid();
    };


    private State[][] calculateNextGeneration() {
        State[][] newGeneration = new State[gridSize][gridSize];
        for (int r = 0; r < gridSize; r++) {
            for (int c = 0; c < gridSize; c++) {
                State[] neighbours = finder.getNeighboursStateFrom(r,c);
                State prevState = grid[r][c];
                State newState = (prevState == LIVE) ?
                        anyLiveCellWithTwoOrThreeLiveNeighborsSurvives.apply(neighbours) :
                        anyDeadCellWithThreeLiveNeighborsBecomesLiveCell.apply(neighbours);
                newGeneration[r][c] = newState;
            }
        }
        return newGeneration;
    };

    private int[][] getBinaryGrid() {
        int[][] intGrid = new int[gridSize][gridSize];
        for (int r = 0; r < gridSize; r++) {
            for (int c = 0; c < gridSize; c++) {
                State state = grid[r][c];
                intGrid[r][c] = state.getValue();
            }
        }
        return intGrid;
    }


    class NeighbourFinder {

        /* Every cell interacts with its eight neighbours */
        protected State[] getNeighboursStateFrom(int row, int column) {
            State[] neighbours = new State[8];
            neighbours[0] = getStateFrom(row-1,column-1);
            neighbours[1] = getStateFrom(row-1, column);
            neighbours[2] = getStateFrom(row-1, column+1);
            neighbours[3] = getStateFrom(row,column-1);
            neighbours[4] = getStateFrom(row,column+1);
            neighbours[5] = getStateFrom(row+1,column-1);
            neighbours[6] = getStateFrom(row+1,column);
            neighbours[7] = getStateFrom(row+1,column+1);
            return neighbours;
        }

        private State getStateFrom(int row, int column) {
            int r = rollUpIfNecessary(row);
            int c = rollUpIfNecessary(column);
            return grid[r][c];
        }

        private int rollUpIfNecessary(int value) {
            if (value == -1) return gridSize-1;
            else if (value == gridSize) return 0;
            return value;
        }

    }


    protected NeighbourFinder getFinder() {
        return finder;
    }

}