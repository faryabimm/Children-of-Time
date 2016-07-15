package com.childrenOfTime.controller;

import com.childrenOfTime.model.ChildrenOfTime;
import com.childrenOfTime.model.Player;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

/**
 * Created by mohammadmahdi on 5/7/16.
 */
public class GameEngine {

    public static final Toolkit DEFAULT_TOOLKIT = Toolkit.getDefaultToolkit();


    private ChildrenOfTime childrenOfTime = ChildrenOfTime.getInstance();

    public void startGame() throws IOException, FontFormatException {
        SwingUtilities.invokeLater(ChildrenOfTime::showLoadingScreen);
//        childrenOfTime.startSinglePlayerMode();
    }

    public static void main(String[] args) {
        GameEngine gameEngine = new GameEngine();
        try {
            gameEngine.startGame();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (FontFormatException e) {
            e.printStackTrace();
        }
    }




}
