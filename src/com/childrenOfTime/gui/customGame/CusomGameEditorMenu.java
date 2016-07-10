package com.childrenOfTime.gui.customGame;

import com.childrenOfTime.gui.customizedElements.CustomizedJButton;
import com.childrenOfTime.gui.customizedElements.MenuScreenPanel;
import com.childrenOfTime.gui.fillForms.AddEffectDialog;
import com.childrenOfTime.gui.fillForms.NewAbilityCreationDialog;
import com.childrenOfTime.gui.fillForms.NewItemCreationDialog;
import com.childrenOfTime.model.ChildrenOfTime;

import javax.swing.*;
import java.awt.*;

/**
 * Created by mohammadmahdi on 7/8/16.
 */
public class CusomGameEditorMenu extends MenuScreenPanel {


    @Override
    public void initialize() {


        JButton customEffect = new CustomizedJButton("new Effect");
        JButton customStore = new CustomizedJButton("new Store");
//        JButton customFoe = new CustomizedJButton("new Foe");



        JButton customScenario = new CustomizedJButton("new Scenario");
        JButton customBattle = new CustomizedJButton("new Battle");
        JButton customWarriorClass = new CustomizedJButton("new WarriorClass");
        JButton customWarrior = new CustomizedJButton("new Warrior");
        JButton customItem   = new CustomizedJButton("new Item");
        JButton customAbility = new CustomizedJButton("new Ability");

        JButton back = new CustomizedJButton("Back to CG Hub");

        back.setBackground(Color.red);
        back.setForeground(Color.yellow);

        this.add(back);
        this.add(customAbility);
        this.add(customItem);
        this.add(customWarrior);
        this.add(customWarriorClass);
        this.add(customBattle);
        this.add(customScenario);
//        this.add(customFoe);
        this.add(customStore);
        this.add(customEffect);

        back.setLocation(ELEMENT_GAP, ChildrenOfTime.PREFERRED_HEIGHT - CustomizedJButton.BUTTON_HEIGHT - ELEMENT_GAP);
        customItem.setLocation(ELEMENT_GAP, ChildrenOfTime.PREFERRED_HEIGHT - 2*CustomizedJButton.BUTTON_HEIGHT - 2*ELEMENT_GAP);
        customAbility.setLocation(ELEMENT_GAP, ChildrenOfTime.PREFERRED_HEIGHT - 3*CustomizedJButton.BUTTON_HEIGHT - 3*ELEMENT_GAP);
        customWarrior.setLocation(ELEMENT_GAP, ChildrenOfTime.PREFERRED_HEIGHT - 4*CustomizedJButton.BUTTON_HEIGHT - 4*ELEMENT_GAP);
        customWarriorClass.setLocation(ELEMENT_GAP, ChildrenOfTime.PREFERRED_HEIGHT - 5*CustomizedJButton.BUTTON_HEIGHT - 5*ELEMENT_GAP);

//        customFoe.setLocation(2*ELEMENT_GAP + CustomizedJButton.BUTTON_WIDTH, ChildrenOfTime.PREFERRED_HEIGHT - CustomizedJButton.BUTTON_HEIGHT - ELEMENT_GAP);
        customStore.setLocation(2*ELEMENT_GAP + CustomizedJButton.BUTTON_WIDTH, ChildrenOfTime.PREFERRED_HEIGHT - 2*CustomizedJButton.BUTTON_HEIGHT - 2*ELEMENT_GAP);
        customEffect.setLocation(2*ELEMENT_GAP + CustomizedJButton.BUTTON_WIDTH, ChildrenOfTime.PREFERRED_HEIGHT - 3*CustomizedJButton.BUTTON_HEIGHT - 3*ELEMENT_GAP);

        customBattle.setLocation(2*ELEMENT_GAP + CustomizedJButton.BUTTON_WIDTH, ChildrenOfTime.PREFERRED_HEIGHT - 4*CustomizedJButton.BUTTON_HEIGHT - 4*ELEMENT_GAP);
        customScenario.setLocation(2*ELEMENT_GAP + CustomizedJButton.BUTTON_WIDTH, ChildrenOfTime.PREFERRED_HEIGHT - 5*CustomizedJButton.BUTTON_HEIGHT - 5*ELEMENT_GAP);


        back.addActionListener(e -> ChildrenOfTime.changeContentPane(new CustomGameUserHubPanel()));
        customScenario.addActionListener(e -> ChildrenOfTime.changeContentPane(new CustomScenarioBuilderPanel()));
        customAbility.addActionListener(e -> {
            fade();
            new NewAbilityCreationDialog();
        });
        customItem.addActionListener(e -> {
            fade();
            new NewItemCreationDialog();
        });
        customEffect.addActionListener(e -> {
            fade();
            new AddEffectDialog(true);
        });
        emerge();



    }
}
