package com.tiagoamp.gameoflife.rules;

import com.tiagoamp.gameoflife.model.State;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.tiagoamp.gameoflife.model.State.DEAD;
import static com.tiagoamp.gameoflife.model.State.LIVE;
import static com.tiagoamp.gameoflife.rules.Rules.anyDeadCellWithThreeLiveNeighborsBecomesLiveCell;
import static com.tiagoamp.gameoflife.rules.Rules.anyLiveCellWithTwoOrThreeLiveNeighborsSurvives;
import static org.junit.jupiter.api.Assertions.*;

class RulesTest {

    @DisplayName("When live cell has two live neighbours")
    @Test
    void anyLiveCellWithTwoOrThreeLiveNeighborsSurvives_whenTwoLiveNeighbours() {
        State[] neighbours = {LIVE, LIVE, DEAD, DEAD, DEAD, DEAD, DEAD, DEAD};
        State nextGenerationState = anyLiveCellWithTwoOrThreeLiveNeighborsSurvives.apply(neighbours);
        assertEquals(LIVE, nextGenerationState,"should have survived with two live neighbours");
    }

    @DisplayName("When live cell has three live neighbours")
    @Test
    void anyLiveCellWithTwoOrThreeLiveNeighborsSurvives_whenThreeLiveNeighbours() {
        State[] neighbours = {LIVE, LIVE, LIVE, DEAD, DEAD, DEAD, DEAD, DEAD};
        State nextGenerationState = anyLiveCellWithTwoOrThreeLiveNeighborsSurvives.apply(neighbours);
        assertEquals(LIVE, nextGenerationState,"should have survived with three live neighbours");
    }

    @DisplayName("When live cell has one live neighbour")
    @Test
    void anyLiveCellWithTwoOrThreeLiveNeighborsSurvives_whenOneLiveNeighbour() {
        State[] neighbours = {LIVE, DEAD, DEAD, DEAD, DEAD, DEAD, DEAD, DEAD};
        State nextGenerationState = anyLiveCellWithTwoOrThreeLiveNeighborsSurvives.apply(neighbours);
        assertEquals(DEAD, nextGenerationState,"should not have survived, as if by underpopulation");
    }

    @DisplayName("When dead cell has less than three neighbours")
    void anyDeadCellWithThreeLiveNeighborsBecomesLiveCell_whenTwoLiveNeighbours() {
        State[] neighbours = {LIVE, LIVE, DEAD, DEAD, DEAD, DEAD, DEAD, DEAD};
        State nextGenerationState = anyDeadCellWithThreeLiveNeighborsBecomesLiveCell.apply(neighbours);
        assertEquals(DEAD, nextGenerationState,"should remain dead");
    }

    @DisplayName("When dead cell has three neighbours")
    void anyDeadCellWithThreeLiveNeighborsBecomesLiveCell_whenThreeLiveNeighbours() {
        State[] neighbours = {LIVE, LIVE, LIVE, DEAD, DEAD, DEAD, DEAD, DEAD};
        State nextGenerationState = anyDeadCellWithThreeLiveNeighborsBecomesLiveCell.apply(neighbours);
        assertEquals(LIVE, nextGenerationState,"should get alive, as if by reproduction");
    }

    @DisplayName("When dead cell has more than three neighbours")
    void anyDeadCellWithThreeLiveNeighborsBecomesLiveCell_whenFourNeighbours() {
        State[] neighbours = {LIVE, LIVE, LIVE, LIVE, DEAD, DEAD, DEAD, DEAD};
        State nextGenerationState = anyDeadCellWithThreeLiveNeighborsBecomesLiveCell.apply(neighbours);
        assertEquals(DEAD, nextGenerationState,"should remain dead");
    }

}