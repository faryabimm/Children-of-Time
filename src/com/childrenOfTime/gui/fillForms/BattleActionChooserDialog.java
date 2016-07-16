package com.childrenOfTime.gui.fillForms;

import com.childrenOfTime.gui.fillForms.dataHolders.AbilityWrapper;
import com.childrenOfTime.gui.fillForms.dataHolders.ItemWrapper;
import com.childrenOfTime.gui.notification.NotificationType;
import com.childrenOfTime.gui.singlePlayer.battleScreen.BattleScreenPanel;
import com.childrenOfTime.model.Equip.AbilComps.Ability;
import com.childrenOfTime.model.Equip.ItemComps.Item;
import com.childrenOfTime.model.Rules;
import com.childrenOfTime.model.Warriors.ActionType;
import com.childrenOfTime.model.Warriors.Warrior;
import com.childrenOfTime.utilities.GUIUtils;
import com.sun.istack.internal.Nullable;

import javax.swing.*;
import java.awt.event.*;

public class BattleActionChooserDialog extends JDialog {
    private JPanel contentPane;
    private JButton giveAttackButton;
    private JButton burnEPButton;
    private JButton castAnAbilityButton;
    private JButton useAnItemButton;

    private ActionType actionType;

    private Warrior warrior;
    private BattleScreenPanel source;

    public BattleActionChooserDialog(Warrior warrior, BattleScreenPanel battleScreenPanel) {

        this.source = battleScreenPanel;
        this.warrior = warrior;
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(giveAttackButton);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

        giveAttackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actionType = ActionType.Attack;
                dispose();
                chooseTargets(Rules.Quantitiy_Of_Targets_For_Manual_Multiple_Target_Choosing, warrior, actionType, null, null);
            }
        });
        burnEPButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!warrior.getInfo().getCanBurnEP()) {
                    GUIUtils.showNotification("this Warrior: " + warrior.getName() + " (" + warrior.getInfo().getClassName() + ") " + warrior.getId() + " cannot burn EP", NotificationType.ERROR);
                } else {
                    actionType = ActionType.BurnEP;
                    dispose();
                    chooseTargets(Rules.Quantitiy_Of_Targets_For_Manual_Multiple_Target_Choosing, warrior, actionType, null, null);
                }
            }
        });
        castAnAbilityButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                AbilityWrapper selectedAbility = new AbilityWrapper();
                new AbilityChooserByWarriorDialog(warrior, selectedAbility);
                actionType = ActionType.AbilityCast;
                dispose();
                chooseTargets(1, warrior, actionType, selectedAbility.ability, null);
            }
        });
        useAnItemButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                ItemWrapper selectedItem = new ItemWrapper();
                new ItemChooserByWarriorDialog(warrior, selectedItem);
                actionType = ActionType.UseItem;
                dispose();

                chooseTargets(selectedItem.item.getTargetType().getNumberOftargetsNeededToChoose(), warrior, actionType, null, selectedItem.item);
            }
        });


        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    private void chooseTargets(int maxNumberOfTargets, Warrior executingWarrior, ActionType executionType, @Nullable Ability castedAbility, @Nullable Item usedItem) {

        source.warriorChoosingWizard(maxNumberOfTargets, executingWarrior, executionType, castedAbility, usedItem);
    }
    private void onCancel() {
// add your code here if necessary
        dispose();
    }
    public static void main(String[] args) {
//        BattleActionChooserDialog dialog = new BattleActionChooserDialog();
        System.exit(0);
    }
}
