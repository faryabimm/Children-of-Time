package com.childrenOfTime.gui.fillForms;

import com.childrenOfTime.cgd.CustomGameDAO;
import com.childrenOfTime.gui.customGame.CusomGameEditorMenu;
import com.childrenOfTime.gui.fillForms.dataHolders.StoreDataHolder;
import com.childrenOfTime.model.ChildrenOfTime;
import com.childrenOfTime.model.Equip.ItemComps.Item;

import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class NewStoreCreationDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JComboBox comboBox1;
    private JLabel label1;
    private JButton addItemButton;
    private JCheckBox acceptsBargainsCheckBox;
    private JCheckBox canInflatePricesCheckBox;
    private JTextField textField1;
    private JTextField textField2;

    StoreDataHolder dataHolder;


    public NewStoreCreationDialog(StoreDataHolder dataHolder) {
        this.dataHolder = dataHolder;

        for (Item storeItem : dataHolder.storeItems) {
            label1.setText(label1.getText() + " " + storeItem.getName());
        }


        ArrayList<String> itemNames = CustomGameDAO.currentUserCustomItems.stream().map(Item::getName).
                collect(Collectors.toCollection(ArrayList::new));

        comboBox1.setModel(new DefaultComboBoxModel(itemNames.toArray()));


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
        canInflatePricesCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (canInflatePricesCheckBox.isSelected()) textField1.setEnabled(true);
                else textField1.setEnabled(false);
            }
        });


        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        addItemButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }

    private void onOK() {
// add your code here
        collectData();
        disposalProcess();
    }

    private void collectData() {
        dataHolder.canInflatePrices = canInflatePricesCheckBox.isSelected();
        dataHolder.acceptsBargains = acceptsBargainsCheckBox.isSelected();
        dataHolder.inflationRate = Integer.parseInt(textField1.getText());
        dataHolder.storeName = textField2.getText();
    }

    private void disposalProcess() {
        ChildrenOfTime.changeContentPane(new CusomGameEditorMenu());
        dispose();
    }

    private void onCancel() {
// add your code here if necessary
        disposalProcess();
    }

    public static void main(String[] args) {
        NewStoreCreationDialog dialog = new NewStoreCreationDialog(new StoreDataHolder());
        System.exit(0);
    }
}
