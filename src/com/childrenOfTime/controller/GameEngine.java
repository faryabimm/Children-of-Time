package com.childrenOfTime.controller;

import com.childrenOfTime.model.ChildrenOfTime;

import javax.swing.*;
import java.awt.*;

/**
 * Created by mohammadmahdi on 5/7/16.
 */
public class GameEngine {


    private ChildrenOfTime childrenOfTime = ChildrenOfTime.getInstance();

    public void startGame() {
        SwingUtilities.invokeLater(ChildrenOfTime.getInstance()::createAndShowGUI);
        childrenOfTime.startSinglePlayerMode();
    }

    public static void main(String[] args) {
        GameEngine gameEngine = new GameEngine();
        gameEngine.startGame();
    }




}
