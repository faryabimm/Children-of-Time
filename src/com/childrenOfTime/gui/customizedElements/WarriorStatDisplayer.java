package com.childrenOfTime.gui.customizedElements;


import com.childrenOfTime.model.ChildrenOfTime;
import com.childrenOfTime.model.Warriors.Warrior;
import com.childrenOfTime.utilities.GUIUtils;

import javax.swing.*;
import java.awt.*;


public class WarriorStatDisplayer extends JPanel {

    public static final int PANEL_WIDTH = 100;
    public static final int PANEL_HEIGHT = 150;
    public static final int PANEL_BORDER_GAP = 5;
    public static final int IMAGE_DIMENTION = PANEL_WIDTH - 2 * PANEL_BORDER_GAP;
    public static final int INDICATOR_BAR_HEIGHT = 2;


    private int maxHealth = 100;
    private int maxMagic = 100;
    private int maxEP = 100;
    private int currentHealth = 70;
    private int currentMagic = 60;
    private int currentEP = 50;

    int HealthBarWidth = PANEL_WIDTH - 2 * PANEL_BORDER_GAP;
    int MagicBarWidth = PANEL_WIDTH - 2 * PANEL_BORDER_GAP;
    int EPBarWidth = PANEL_WIDTH - 2 * PANEL_BORDER_GAP;
    ImageIcon imageIcon;

    private Warrior warrior;

    public WarriorStatDisplayer(Warrior warrior) {

        this.warrior = warrior;

        this.setPreferredSize(new Dimension(WarriorStatDisplayer.PANEL_WIDTH, WarriorStatDisplayer.HEIGHT));
        updateInformation();
        calculateBarWidths();
        imageIcon = warrior.getImage();
        imageIcon = GUIUtils.getScaledIcon(imageIcon, IMAGE_DIMENTION, IMAGE_DIMENTION, Image.SCALE_SMOOTH);
        this.setLayout(null);
//        JLabel hpLabel = new JLabel("  HP", SwingConstants.LEFT);
//        hpLabel.setBackground(ChildrenOfTime.GREY);
//        JLabel mpLabel = new JLabel("  MP", SwingConstants.LEFT);
//        mpLabel.setBackground(ChildrenOfTime.GREY);
//        JLabel epLabel = new JLabel("  EP", SwingConstants.LEFT);
//        epLabel.setBackground(ChildrenOfTime.GREY);

//        hpLabel.setForeground(Color.white);
//        mpLabel.setForeground(Color.white);
//        epLabel.setForeground(Color.white);
//
//
//        hpLabel.setLocation(PANEL_BORDER_GAP,2 * PANEL_BORDER_GAP + IMAGE_DIMENTION);
//        mpLabel.setLocation(PANEL_BORDER_GAP,3 * PANEL_BORDER_GAP + IMAGE_DIMENTION);
//        epLabel.setLocation(PANEL_BORDER_GAP,4 * PANEL_BORDER_GAP + IMAGE_DIMENTION);

//        for (int i = 0; i < 13; i++) {
//            this.add(new JLabel(" "));
//        }
//        this.add(hpLabel);
//        this.add(new JLabel(" "));
//        this.add(mpLabel);
//        this.add(new JLabel(" "));
//        this.add(epLabel);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(ChildrenOfTime.GREY);
        g2d.fillRect(0, 0, PANEL_WIDTH, PANEL_HEIGHT);
        g2d.drawImage(GUIUtils.iconToImage(imageIcon), PANEL_BORDER_GAP, PANEL_BORDER_GAP, IMAGE_DIMENTION, IMAGE_DIMENTION, this);


        updateInformation();
        calculateBarWidths();


        g2d.setColor(Color.red);
        g2d.fillRect((int) (PANEL_BORDER_GAP * 1.2), 2 * PANEL_BORDER_GAP + IMAGE_DIMENTION, HealthBarWidth, INDICATOR_BAR_HEIGHT);
        g2d.setColor(Color.blue);
        g2d.fillRect((int) (PANEL_BORDER_GAP * 1.2), 3 * PANEL_BORDER_GAP + IMAGE_DIMENTION, MagicBarWidth, INDICATOR_BAR_HEIGHT);
        g2d.setColor(Color.yellow);
        g2d.fillRect((int) (PANEL_BORDER_GAP * 1.2), 4 * PANEL_BORDER_GAP + IMAGE_DIMENTION, EPBarWidth, INDICATOR_BAR_HEIGHT);
    }

    private void updateInformation() {
        currentEP = warrior.getCurrentEP();
        currentHealth = warrior.getCurrentHealth();
        currentMagic = warrior.getCurrentMagic();
        maxEP = warrior.getInfo().getInitialEP();
        maxHealth = warrior.getMaxHealth();
        maxMagic = warrior.getMaxMagic();
    }

    private void calculateBarWidths() {
        HealthBarWidth = (int) ((double) currentHealth / (double) maxHealth * (PANEL_WIDTH - 2 * PANEL_BORDER_GAP));
        MagicBarWidth = (int) ((double) currentMagic / (double) maxMagic * (PANEL_WIDTH - 2 * PANEL_BORDER_GAP));
        EPBarWidth = (int) ((double) currentEP / (double) maxEP * (PANEL_WIDTH - 2 * PANEL_BORDER_GAP));
    }

    public static void main(String[] args) {
//        JFrame frame = new JFrame();
//        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
//        frame.setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
//        JPanel panel = new WarriorStatDisplayer(/*new Warrior("Havij",null,null,null)*/);
//
//        frame.setContentPane(panel);
//
//        frame.pack();
//        frame.setLocationRelativeTo(null);
//        frame.setVisible(true);

    }
}
