package com.tiagoamp.gameoflife.controller;

import com.tiagoamp.gameoflife.model.Universe;
import com.tiagoamp.gameoflife.model.UniverseBuilder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class GameController {

    private Universe universe;
    private final int GRID_SIZE = 20;

    @GetMapping(value = "game", produces = "application/json")
    public int[][] fetchNextGeneration() {
        return getUniverse().updateToNextGeneration();
    }


    private Universe getUniverse() {
        if (universe == null)
            universe = UniverseBuilder.fromRandomState(GRID_SIZE);
        return universe;
    }
}
