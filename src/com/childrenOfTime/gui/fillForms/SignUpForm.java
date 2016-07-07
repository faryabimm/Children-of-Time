package com.childrenOfTime.gui.fillForms;

import com.childrenOfTime.cgd.User;
import com.childrenOfTime.exceptions.ExistingUserException;
import com.childrenOfTime.gui.customGame.CustomGameMenuScreenPanel;
import com.childrenOfTime.model.ChildrenOfTime;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by mohammadmahdi on 7/7/16.
 */
public class SignUpForm extends JDialog{
    private JPanel signUpForm;
    private JButton create;
    private JButton cancelButton;
    private JLabel userNameLabel;
    private JLabel passwordLabel;
    private JTextField textField1;
    private JTextField textField2;


    static JFrame frame;


    public SignUpForm() {

        this.setBackground(ChildrenOfTime.GREY);
        create.setEnabled(false);
        setListeners();

    }
    public static void main(String[] args) {
        frame = new JFrame("Create a new User");
        frame.setResizable(false);
        frame.setContentPane(new SignUpForm().signUpForm);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
    private void setListeners() {
        textField1.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                super.focusLost(e);
                Pattern p = Pattern.compile("((?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{6,20})");
                Matcher m = p.matcher(textField1.getText());
                if (!m.matches()) {
                    textField1.setBackground(Color.red);
                    create.setEnabled(false);
                } else {
                    textField1.setBackground(Color.green);
                    if (textField2.getBackground().equals(Color.green)) create.setEnabled(true);
                }
            }
            @Override
            public void focusGained(FocusEvent e) {
                super.focusGained(e);
                textField1.setBackground(Color.white);
            }
        });
        textField2.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                super.focusLost(e);
                Pattern p = Pattern.compile("^(?=.{8,20}$)(?![_.])(?!.*[_.]{2})[a-zA-Z0-9._]+(?<![_.])$");
                Matcher m = p.matcher(textField2.getText());
                if (!m.matches()) {
                    textField2.setBackground(Color.red);
                    create.setEnabled(false);
                } else {
                    textField2.setBackground(Color.green);
                    if (textField1.getBackground().equals(Color.green)) create.setEnabled(true);
                }
            }
            @Override
            public void focusGained(FocusEvent e) {
                super.focusGained(e);
                textField2.setBackground(Color.white);
            }
        });
        create.addActionListener(e -> {
            try {
                new User(textField1.getText(), textField2.getText());
                JOptionPane.showMessageDialog(frame, "new User successfully created!", "User created", JOptionPane.INFORMATION_MESSAGE);
                close();
            }catch (ExistingUserException e2) {
                JOptionPane.showMessageDialog(frame, e2.getMessage(), "Existing user", JOptionPane.ERROR_MESSAGE);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });
        cancelButton.addActionListener(e -> {
            close();
        });


    }

    private void close() {
        ChildrenOfTime.changeContentPane(new CustomGameMenuScreenPanel());
        ChildrenOfTime.frame.setEnabled(true);
        frame.dispose();

    }
}
