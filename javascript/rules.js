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

const rules = {

    anyLiveCellWithTwoOrThreeLiveNeighborsSurvives : (neighbours) => {
        const liveNeighboursAmount = neighbours.reduce((acc,curr) => acc+curr, 0);
        return liveNeighboursAmount == 2 || liveNeighboursAmount == 3 ? 1 : 0;
    },

    anyDeadCellWithThreeLiveNeighborsBecomesLiveCell : (neighbours) => {
        const liveNeighboursAmount = neighbours.reduce((acc,curr) => acc+curr, 0);
        return liveNeighboursAmount == 3 ? 1 : 0;
    }

};

module.exports = rules;