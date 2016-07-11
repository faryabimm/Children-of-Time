package com.childrenOfTime.gui.fillForms;

import com.childrenOfTime.cgd.CustomGameDAO;
import com.childrenOfTime.gui.customGame.CusomGameEditorMenu;
import com.childrenOfTime.gui.fillForms.dataHolders.WarriorDataHolder;
import com.childrenOfTime.model.ChildrenOfTime;
import com.childrenOfTime.model.Equip.AbilComps.Ability;
import com.childrenOfTime.model.Warriors.HeroClass;
import com.childrenOfTime.utilities.GUIUtils;

import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class NewWarriorCreationDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField textField1;
    private JComboBox comboBox1;
    private JButton setSpescificAbilitiesButton;
    private JButton chooseImageFileButton;
    private JCheckBox noImageFileSelectedCheckBox;
    private WarriorDataHolder dataHolder;

    private String imageFilePath = null;

    public NewWarriorCreationDialog(WarriorDataHolder dataHolder) {
        this.dataHolder = dataHolder;


        ArrayList<String> warriorClassNames = CustomGameDAO.currentUserCustomWarriorClasses.stream().map(HeroClass::getClassName).
                collect(Collectors.toCollection(ArrayList::new));
        comboBox1.setModel(new DefaultComboBoxModel(warriorClassNames.toArray()));

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

        setSpescificAbilitiesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AbilityChooserDialog(dataHolder.specificAbilities);
            }
        });
        chooseImageFileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                imageFilePath = GUIUtils.getPNGImageFilePath(null);
                try {
                    noImageFileSelectedCheckBox.setText("..." + imageFilePath.substring(imageFilePath.length() - 15,
                            imageFilePath.length()));
                } catch (NullPointerException e1) {
                }
                if (imageFilePath != null) {
                    noImageFileSelectedCheckBox.setSelected(true);
                }
            }
        });

        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    private void onOK() {
// add your code here
        collectData();
        disposalProcess();
    }

    private void disposalProcess() {
        ChildrenOfTime.changeContentPane(new CusomGameEditorMenu());
        dispose();
    }
    private void collectData() {
        dataHolder.operationCancelled = false;
        dataHolder.warriorClass = CustomGameDAO.currentUserCustomWarriorClasses.get(comboBox1.getSelectedIndex());
        dataHolder.name = textField1.getText();
        dataHolder.imageFilePath = imageFilePath;
    }

    private void onCancel() {
// add your code here if necessary
        disposalProcess();
    }

    public static void main(String[] args) {
//        NewWarriorCreationDialog dialog = new NewWarriorCreationDialog();
        System.exit(0);
    }
}
