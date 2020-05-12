package com.tiagoamp.gameoflife.rules;

import com.tiagoamp.gameoflife.model.State;

import java.util.Arrays;
import java.util.function.Function;

import static com.tiagoamp.gameoflife.model.State.DEAD;
import static com.tiagoamp.gameoflife.model.State.LIVE;

/*  As of Wikipedia:  https://en.wikipedia.org/wiki/Conway%27s_Game_of_Life

    Rules:

    At each step in time, the following transitions occur:
    - Any live cell with fewer than two live neighbours dies, as if by underpopulation.
    - Any live cell with two or three live neighbours lives on to the next generation.
    - Any live cell with more than three live neighbours dies, as if by overpopulation.
    - Any dead cell with exactly three live neighbours becomes a live cell, as if by reproduction.

    These rules, which compare the behavior of the automaton to real life, can be condensed into the following:
    - Any live cell with two or three live neighbors survives.
    - Any dead cell with three live neighbors becomes a live cell.
    - All other live cells die in the next generation. Similarly, all other dead cells stay dead.

 */
public abstract class Rules {

    public static Function<State[], State> anyLiveCellWithTwoOrThreeLiveNeighborsSurvives = neighbours -> {
        int liveNeighboursAmount = Arrays.stream(neighbours).mapToInt(state -> state.getValue()).sum();
        return liveNeighboursAmount == 2 || liveNeighboursAmount == 3 ? LIVE : DEAD;
    };

    public static Function<State[], State> anyDeadCellWithThreeLiveNeighborsBecomesLiveCell = neighbours -> {
        int liveNeighboursAmount = Arrays.stream(neighbours).mapToInt(state -> state.getValue()).sum();
        return liveNeighboursAmount == 3 ? LIVE : DEAD;
    };

}