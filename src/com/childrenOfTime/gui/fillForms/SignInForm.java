package com.childrenOfTime.gui.fillForms;

import com.childrenOfTime.cgd.CustomGameDAO;
import com.childrenOfTime.cgd.User;
import com.childrenOfTime.gui.customGame.CustomGameMenuScreenPanel;
import com.childrenOfTime.gui.customGame.CustomGameUserHubPanel;
import com.childrenOfTime.model.ChildrenOfTime;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by mohammadmahdi on 7/7/16.
 */
public class SignInForm extends JDialog{
    private JPanel signInForm;
    private JLabel passwordLabel;
    private JLabel userNameLabel;
    private JButton signIn;
    private JButton cancelButton;
    private JTextField textField1;
    private JTextField textField2;


    public SignInForm() {

        this.setBackground(ChildrenOfTime.GREY);
        signIn.setEnabled(false);
        setListeners();

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
                    signIn.setEnabled(false);
                } else {
                    textField1.setBackground(Color.green);
                    if (textField2.getBackground().equals(Color.green)) signIn.setEnabled(true);
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
                    signIn.setEnabled(false);
                } else {
                    textField2.setBackground(Color.green);
                    if (textField1.getBackground().equals(Color.green)) signIn.setEnabled(true);
                }
            }
            @Override
            public void focusGained(FocusEvent e) {
                super.focusGained(e);
                textField2.setBackground(Color.white);
            }
        });
        signIn.addActionListener(e -> {
            if (!User.users.contains(new User(textField1.getText()))) {
                JOptionPane.showMessageDialog(frame, "Entered username is not registered yet!", "Username no found!", JOptionPane.ERROR_MESSAGE);
            } else {
                if (!User.users.get(User.users.indexOf(new User(textField1.getText()))).getPassWord().equals(textField2.getText())) {
                    JOptionPane.showMessageDialog(frame, "Entered password doesn't match database!", "Wrong password", JOptionPane.ERROR_MESSAGE);
                } else {
                    CustomGameDAO.currentUser = User.users.get(User.users.indexOf(new User(textField1.getText())));
                    JOptionPane.showMessageDialog(frame, "welcome " + CustomGameDAO.currentUser.getUserName() + "!", "Welcome!", JOptionPane.INFORMATION_MESSAGE);
                    loadUserHub();
                }
            }
        });
        cancelButton.addActionListener(e -> {
            close();
        });


    }

    private void loadUserHub() {
        ChildrenOfTime.changeContentPane(new CustomGameUserHubPanel());
        ChildrenOfTime.frame.setEnabled(true);
        frame.dispose();
    }

    private void close() {
        ChildrenOfTime.changeContentPane(new CustomGameMenuScreenPanel());
        ChildrenOfTime.frame.setEnabled(true);
        frame.dispose();

    }

    static JFrame frame;

    public static void main(String[] args) {
        frame = new JFrame("Log In ...");
        frame.setResizable(false);
        frame.setContentPane(new SignInForm().signInForm);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

}
