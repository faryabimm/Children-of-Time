package com.childrenOfTime.gui.fillForms;

import com.childrenOfTime.cgd.CustomGameDAO;
import com.childrenOfTime.model.Equip.Effect;

import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class EffectChooserDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JComboBox comboBox1;
    private JButton addEffectButton;
    private JLabel label1;

    ArrayList<Effect> selectedEffects;

    public EffectChooserDialog(ArrayList<Effect> selectedEffects) {
        this.selectedEffects = selectedEffects;

        for (Effect selectedEffect : selectedEffects) {
            label1.setText(label1.getText() + " " + selectedEffect.getName());
        }

        ArrayList<String> effectNames = CustomGameDAO.currentUserCustomEffects.stream().map(Effect::getName).
                collect(Collectors.toCollection(ArrayList::new));

        comboBox1.setModel(new DefaultComboBoxModel(effectNames.toArray()));






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


        addEffectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Effect toAdd = CustomGameDAO.currentUserCustomEffects.get(comboBox1.getSelectedIndex());
                if(!selectedEffects.contains(toAdd)) {
                    selectedEffects.add(toAdd);
                    label1.setText(label1.getText() + " " + toAdd.getName());
                }
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
//        EffectChooserDialog dialog = new EffectChooserDialog();

        System.exit(0);
    }
}
