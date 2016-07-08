package com.childrenOfTime.gui.customGame;

import com.childrenOfTime.gui.MainMenuScreenPanel;
import com.childrenOfTime.gui.customizedElements.CustomizedJButton;
import com.childrenOfTime.gui.customizedElements.MenuScreenPanel;
import com.childrenOfTime.model.ChildrenOfTime;

import javax.swing.*;

/**
 * Created by mohammadmahdi on 7/8/16.
 */
public class CusomGameEditorMenu extends MenuScreenPanel {


    @Override
    public void initialize() {


        JButton customScenario = new CustomizedJButton("new Scenario");
        JButton customBattle = new CustomizedJButton("new Battle");
        JButton customHeroClass = new CustomizedJButton("new HeroClass");
        JButton customHero = new CustomizedJButton("new Hero");
        JButton customItem   = new CustomizedJButton("new Item");
        JButton customAbility = new CustomizedJButton("new Ability");

        JButton back = new CustomizedJButton("Back to CG Hub");

        this.add(back);
        this.add(customAbility);
        this.add(customItem);
        this.add(customHero);
        this.add(customHeroClass);
        this.add(customBattle);
        this.add(customScenario);

        back.setLocation(ELEMENT_GAP, ChildrenOfTime.PREFERRED_HEIGHT - CustomizedJButton.BUTTON_HEIGHT - ELEMENT_GAP);
        customItem.setLocation(ELEMENT_GAP, ChildrenOfTime.PREFERRED_HEIGHT - 2*CustomizedJButton.BUTTON_HEIGHT - 2*ELEMENT_GAP);
        customAbility.setLocation(ELEMENT_GAP, ChildrenOfTime.PREFERRED_HEIGHT - 3*CustomizedJButton.BUTTON_HEIGHT - 3*ELEMENT_GAP);
        customHero.setLocation(ELEMENT_GAP, ChildrenOfTime.PREFERRED_HEIGHT - 4*CustomizedJButton.BUTTON_HEIGHT - 4*ELEMENT_GAP);
        customHeroClass.setLocation(ELEMENT_GAP, ChildrenOfTime.PREFERRED_HEIGHT - 5*CustomizedJButton.BUTTON_HEIGHT - 5*ELEMENT_GAP);
        customBattle.setLocation(ELEMENT_GAP, ChildrenOfTime.PREFERRED_HEIGHT - 6*CustomizedJButton.BUTTON_HEIGHT - 6*ELEMENT_GAP);
        customScenario.setLocation(ELEMENT_GAP, ChildrenOfTime.PREFERRED_HEIGHT - 7*CustomizedJButton.BUTTON_HEIGHT - 7*ELEMENT_GAP);



        back.addActionListener(e -> ChildrenOfTime.changeContentPane(new CustomGameUserHubPanel()));
        customScenario.addActionListener(e -> ChildrenOfTime.changeContentPane(new customScenarioBuilderPanel()));
        emerge();



    }
}
