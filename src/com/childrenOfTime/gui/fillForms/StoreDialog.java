package com.childrenOfTime.gui.fillForms;

import com.childrenOfTime.model.Equip.AbilComps.Ability;
import com.childrenOfTime.model.Equip.ItemComps.Item;
import com.childrenOfTime.model.Player;
import com.childrenOfTime.model.Store;
import com.childrenOfTime.model.Warriors.Warrior;

import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class StoreDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JComboBox comboBox1;
    private JComboBox comboBox2;
    private JButton buyButton;
    private JButton sellButton;
    private JComboBox comboBox3;

    private Store store;
    private Player player;

    ArrayList<String> selectedWarriorItemsNames = new ArrayList<>();

    public StoreDialog(Player player, Store store) {

        ArrayList<String> storeItemNames = store.getItems().stream().map(Item::getName).collect(Collectors.toCollection(ArrayList::new));

        comboBox1.setModel(new DefaultComboBoxModel(storeItemNames.toArray()));

        ArrayList<String> warriorNames = player.getMyTeam().stream().map(Warrior::getName).collect(Collectors.toCollection(ArrayList::new));

        comboBox2.setModel(new DefaultComboBoxModel(warriorNames.toArray()));


        selectedWarriorItemsNames.addAll(player.getMyTeam().get(0).getInventory().getItems().stream().map(Item::getName).collect(Collectors.toList()));

        comboBox3.setModel(new DefaultComboBoxModel(selectedWarriorItemsNames.toArray()));


        this.player = player;
        this.store = store;


        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

// call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

// call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);


        comboBox2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectedWarriorItemsNames = new ArrayList<String>();
                selectedWarriorItemsNames.addAll(player.getMyTeam().get(comboBox2.getSelectedIndex()).getInventory().getItems().stream().map(Item::getName).collect(Collectors.toList()));
                comboBox3.setModel(new DefaultComboBoxModel(selectedWarriorItemsNames.toArray()));
            }
        });
        buyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Item toBuy = store.getItems().get(comboBox1.getSelectedIndex());
                Warrior target = player.getMyTeam().get(comboBox2.getSelectedIndex());
                player.buy(toBuy, target);
            }
        });
        sellButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Item toSell = player.getMyTeam().get(comboBox2.getSelectedIndex()).getInventory().getItems().get(comboBox3.getSelectedIndex());
                Warrior target = player.getMyTeam().get(comboBox2.getSelectedIndex());
                player.sell(toSell, target);
            }
        });
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    private void onOK() {
// add your code here
        dispose();
    }

    private void onCancel() {
// add your code here if necessary
        dispose();
    }

    public static void main(String[] args) {
//        StoreDialog dialog = new StoreDialog();

        System.exit(0);
    }
}
