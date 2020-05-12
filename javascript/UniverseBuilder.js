const Universe = require('./Universe');

class UniverseBuilder {

    static fromRandomState(gridSize) {
        const seed = [];
        for (let r = 0; r < gridSize; r++) {
            const row = [];
            for (let c = 0; c < gridSize; c++) {
                row[c] = eitherZeroOrOne();
            }
            seed.push(row);
        }
        return new Universe(seed);
    }

}

eitherZeroOrOne = () => {
    const min=0, max=1;  
    return Math.floor(Math.random()*(max-min+1)+min);
}

module.exports = UniverseBuilder;