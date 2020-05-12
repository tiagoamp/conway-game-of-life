var express = require('express');
var cors = require('cors')
var Universe = require('./Universe')
var UniverseBuilder = require('./UniverseBuilder')

var app = express()
app.use(cors())


app.get('/game', function (req, res) {

  const universe = UniverseBuilder.fromRandomState(20);
  let generation = universe.updateToNextGeneration();
  console.log(generation);

  res.send(generation);

});


app.listen(9000, function () {
  console.log('Example app listening on port 9000!');
});

