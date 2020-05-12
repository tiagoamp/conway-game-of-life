package com.tiagoamp.gameoflife.model;

public enum State {

    LIVE(1), DEAD(0);

    private int value;

    private State(int v) {
        this.value = v;
    }

    public static State fromValue(int value) {
        for (State state : State.values()) {
            if (value == state.value) return state;
        }
        throw new IllegalArgumentException("State value informed do not exist: " + value);
    }

    public int getValue() {
        return value;
    }

}
