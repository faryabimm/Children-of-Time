package com.childrenOfTime.gui.customizedElements;

import com.childrenOfTime.gui.customGame.CustomScenarioBuilderPanel;
import com.childrenOfTime.gui.fillForms.dataHolders.CustomScenarioInfoHolder;
import com.childrenOfTime.gui.singlePlayer.BattleScreenPanel;
import com.childrenOfTime.model.Battle;
import com.childrenOfTime.model.ChildrenOfTime;
import com.childrenOfTime.model.Store;
import com.childrenOfTime.model.Story;
import com.childrenOfTime.model.Warriors.Warrior;

import java.util.ArrayList;

/**
 * Created by mohammadmahdi on 7/12/16.
 */
public class PlayerIndicator {

    public static final int INDICATOR_AND_CELL_DIMENSION_DIFFERENCE = 12;
    public static final int PLAYER_INDICATOR_DIAMETER = CustomScenarioBuilderPanel.MAP_CELL_DIMENTION - INDICATOR_AND_CELL_DIMENSION_DIFFERENCE;
    public static final int INDICATOR_INITIAL_X = CustomScenarioBuilderPanel.BORDER_GAP + CustomScenarioBuilderPanel.MAP_CELL_DIMENTION / 2 - PLAYER_INDICATOR_DIAMETER / 2;
    public static final int INDICATOR_INITIAL_Y = INDICATOR_INITIAL_X;
    public static final int MOVING_STEP = CustomScenarioBuilderPanel.MAP_CELL_DIMENTION;


    private int X = INDICATOR_INITIAL_X;
    private int Y = INDICATOR_INITIAL_Y;

    private Scenario scenario;
    private CustomScenarioInfoHolder infoHolder;


    public PlayerIndicator(CustomScenarioInfoHolder infoHolder) {
        this.infoHolder = infoHolder;
        this.scenario = infoHolder.playingScenario;
    }

    private int I = 0;
    private int J = 0;

    public void gotoIJ(int i, int j) {
        int deltaI = i - this.I;
        int deltaJ = j - this.J;
        this.X += i * CustomScenarioBuilderPanel.MAP_CELL_DIMENTION;
        this.Y += j * CustomScenarioBuilderPanel.MAP_CELL_DIMENTION;
    }

    public boolean movingUpIsValid() {
        if (J == 0) return false;

        switch (scenario.getIJ(I, J - 1).getCellType()) {
            case DOOR_LEFT_RIGHT:
            case DOOR_RIGHT_LEFT:
            case DOOR_UP_DOWN:
            case WALL:
                return false;
        }

        return true;
    }

    public boolean movingDownIsValid() {
        if (J == CustomScenarioBuilderPanel.NUMBER_OF_MAP_ROWS - 1) return false;

        switch (scenario.getIJ(I, J + 1).getCellType()) {
            case DOOR_LEFT_RIGHT:
            case DOOR_RIGHT_LEFT:
            case DOOR_DOWN_UP:
            case WALL:
                return false;
        }
        return true;
    }

    public boolean movingLeftIsValid() {
        if (I == 0) return false;

        switch (scenario.getIJ(I - 1, J).getCellType()) {
            case DOOR_LEFT_RIGHT:
            case DOOR_DOWN_UP:
            case DOOR_UP_DOWN:
            case WALL:
                return false;
        }
        return true;
    }

    public boolean movingRightIsValid() {
        if (I == CustomScenarioBuilderPanel.NUMBER_OF_MAP_COLUMNS - 1) return false;

        switch (scenario.getIJ(I + 1, J).getCellType()) {
            case DOOR_RIGHT_LEFT:
            case DOOR_DOWN_UP:
            case DOOR_UP_DOWN:
            case WALL:
                return false;
        }
        return true;
    }

    public void moveUp() {
        if (movingUpIsValid()) {
            this.Y -= MOVING_STEP;
            this.J--;
        }
    }

    public void moveDown() {
        if (movingDownIsValid()) {
            this.Y += MOVING_STEP;
            this.J++;
        }
    }

    public void moveLeft() {
        if (movingLeftIsValid()) {
            this.X -= MOVING_STEP;
            this.I--;
        }
    }

    public void moveRight() {
        if (movingRightIsValid()) {
            this.X += MOVING_STEP;
            this.I++;
        }

    }

    public void interact() {
        switch (scenario.getIJ(I, J).getCellType()) {
            case BATTLE:
            case BOSS:
                loadBattle(scenario.getIJ(I, J).getBattle(), infoHolder.playerWarriors);
                break;
            case STORE:
                loadStore(scenario.getIJ(I, J).getStore(), infoHolder.playerWarriors);
                break;
            case STORY:
                loadStory(scenario.getIJ(I, J).getStory());
                break;
            case UPGRADEPLACE:
                loadUpgradeScreen(infoHolder.playerWarriors);
                break;
        }
    }

    private void loadUpgradeScreen(ArrayList<Warrior> playerWarriors) {

    }

    private void loadStory(Story story) {

    }

    private void loadStore(Store store, ArrayList<Warrior> playerWarriors) {

    }

    private void loadBattle(Battle battle, ArrayList<Warrior> playerWarriors) {
        BattleScreenPanel battleScreenPanel = new BattleScreenPanel(battle, playerWarriors);
        System.out.println("SOUT");
        ChildrenOfTime.changeContentPane(battleScreenPanel);
    }

    public int getX() {
        return X;
    }

    public void setX(int x) {
        X = x;
    }

    public int getY() {
        return Y;
    }

    public void setY(int y) {
        Y = y;
    }
}
