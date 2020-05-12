const rules = require('./rules');

/*
 * The universe of the Game of Life is an infinite, two-dimensional orthogonal grid of square cells.
 */
class Universe {

    constructor(seed) {
        this.grid = seed;
        this.gridSize = seed.length;
    }

    updateToNextGeneration() {
        this.grid = this._calculateNextGeneration();
        return this.grid;
    };


    _calculateNextGeneration() {
        const newGeneration = [];
        for (let r = 0; r < this.gridSize; r++) {
            const row = [];
            for (let c = 0; c < this.gridSize; c++) {
                const neighbours = this.getNeighboursStateFrom(r,c);
                const prevState = this.grid[r][c];
                const newState = (prevState == 1) ?
                        rules.anyLiveCellWithTwoOrThreeLiveNeighborsSurvives(neighbours) :
                        rules.anyDeadCellWithThreeLiveNeighborsBecomesLiveCell(neighbours);
                row[c] = newState;
            }
            newGeneration.push(row);
        }
        return newGeneration;
    };

    /* Every cell interacts with its eight neighbours */
    getNeighboursStateFrom(row,column) {
        const neighbours = [];
        neighbours[0] = this._getStateFrom(row-1,column-1);
        neighbours[1] = this._getStateFrom(row-1, column);
        neighbours[2] = this._getStateFrom(row-1, column+1);
        neighbours[3] = this._getStateFrom(row,column-1);
        neighbours[4] = this._getStateFrom(row,column+1);
        neighbours[5] = this._getStateFrom(row+1,column-1);
        neighbours[6] = this._getStateFrom(row+1,column);
        neighbours[7] = this._getStateFrom(row+1,column+1);
        return neighbours;
    }

    _getStateFrom(row, column) {
        const r = this._rollUpIfNecessary(row);
        const c = this._rollUpIfNecessary(column);
        return this.grid[r][c];
    }

    _rollUpIfNecessary(value) {
        if (value == -1) return this.gridSize-1;
        else if (value == this.gridSize) return 0;
        return value;
    }

}

module.exports = Universe;