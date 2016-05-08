package com.childrenOfTime.controller;

import com.childrenOfTime.model.ChildrenOfTime;

/**
 * Created by mohammadmahdi on 5/7/16.
 */
public class GameEngine {


    private ChildrenOfTime childrenOfTime = ChildrenOfTime.getInstance();

    public void startGame() {
        childrenOfTime.startSinglePlayerMode();
    }

    public static void main(String[] args) {
        GameEngine gameEngine = new GameEngine();
        gameEngine.startGame();
    }
}
