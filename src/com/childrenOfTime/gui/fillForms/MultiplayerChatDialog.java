package com.childrenOfTime.gui.fillForms;

import javax.swing.*;
import java.awt.event.*;

public class MultiplayerChatDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JTextArea textArea1;
    private JTextField textField1;
    String yourUserName;


    public MultiplayerChatDialog(String yourUserName) {

        this.yourUserName = yourUserName;

        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);


        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
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
        textField1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (!textField1.getText().equals("")) {
                    addMessage(textField1.getText(), yourUserName);
                    textField1.setText("");
                }
            }
        });

        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        textField1.grabFocus();
//        textField1.requestFocus();
    }

    private void onOK() {
// add your code here
        this.setVisible(false);
    }

    private void onCancel() {
// add your code here if necessary

        onOK();
    }

    public static void main(String[] args) {
        MultiplayerChatDialog dialog = new MultiplayerChatDialog("Mohammadmahdi");
        dialog.setVisible(true);
    }


    public void addMessage(String message, String senderName) {
        if (!message.equals("")) {
            textArea1.setText(textArea1.getText() + "\n[" + senderName + "]: " + message);
        }
    }
}