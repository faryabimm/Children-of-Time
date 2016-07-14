package com.childrenOfTime.gui.singlePlayer;

import com.childrenOfTime.controller.GameEngine;
import com.childrenOfTime.gui.customizedElements.CustomizedJButton;
import com.childrenOfTime.gui.customizedElements.CustomizedJImage;
import com.childrenOfTime.gui.customizedElements.MenuScreenPanel;
import com.childrenOfTime.gui.customizedListeners.MouseClickListener;
import com.childrenOfTime.gui.fillForms.BattleActionChooserDialog;
import com.childrenOfTime.gui.notification.NotificationType;
import com.childrenOfTime.model.Battle;
import com.childrenOfTime.model.ChildrenOfTime;
import com.childrenOfTime.model.Warriors.Warrior;
import com.childrenOfTime.utilities.GUIUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;


public class BattleScreenPanel extends MenuScreenPanel {

    public static BattleScreenPanel lastState;

    private Battle battle;
    private ArrayList<Warrior> warriors;

    private BodyPanel bodyPanel;
    private InfoIndicatorPanel infoPanel;
    private WarriorIndicatorPanel leftWarriorIndicatiorPanel;
    private WarriorIndicatorPanel rightWarriorIndicatiorPanel;

    public WarriorIndicatorPanel getRightWarriorIndicatiorPanel() {
        return rightWarriorIndicatiorPanel;
    }

    public void setRightWarriorIndicatiorPanel(WarriorIndicatorPanel rightWarriorIndicatiorPanel) {
        this.rightWarriorIndicatiorPanel = rightWarriorIndicatiorPanel;
    }

    public WarriorIndicatorPanel getLeftWarriorIndicatiorPanel() {
        return leftWarriorIndicatiorPanel;
    }

    public void setLeftWarriorIndicatiorPanel(WarriorIndicatorPanel leftWarriorIndicatiorPanel) {
        this.leftWarriorIndicatiorPanel = leftWarriorIndicatiorPanel;
    }

    public ArrayList<Warrior> warriorChoosingWizard(int maximumNumber) {
        ArrayList<Warrior> chosenWarriors = new ArrayList<>();
        changeBodyPane(new WarriorChoosingPanel(this, maximumNumber, chosenWarriors));
        return chosenWarriors;
    }

    public void changeBodyPane(MenuScreenPanel newBodyPane) {
        MenuScreenPanel currentBodyPane = bodyPanel;
        currentBodyPane.fade();
        add(newBodyPane, BorderLayout.CENTER);
        ChildrenOfTime.frame.pack();
        ChildrenOfTime.frame.setLocationRelativeTo(null);
        ChildrenOfTime.frame.setVisible(true);
        newBodyPane.requestFocus();
        newBodyPane.repaint();
        newBodyPane.emerge();
    }

    public BattleScreenPanel(Battle battle, ArrayList<Warrior> warriors) {
        lastState = this;
        this.battle = battle;
        this.warriors = warriors;
        this.setLayout(new BorderLayout());
        this.setPreferredSize(BattleScreenPanelStaticData.BATTLE_SCREEN_PANEL_DIMENTION);
        this.setBackground(ChildrenOfTime.GREY);
        initializePanel();
    }
    public BattleScreenPanel() {
        this.setLayout(new BorderLayout());
        this.setPreferredSize(BattleScreenPanelStaticData.BATTLE_SCREEN_PANEL_DIMENTION);
        this.setBackground(ChildrenOfTime.GREY);
        initializePanel();
    }
    private void initializePanel() {

        bodyPanel = new BodyPanel(warriors.get(0), battle.getDefualtFoes().get(0));
        infoPanel = new InfoIndicatorPanel(warriors.get(0), battle.getDefualtFoes().get(0));
        leftWarriorIndicatiorPanel = new WarriorIndicatorPanel(warriors, true, this);
        rightWarriorIndicatiorPanel = new WarriorIndicatorPanel(battle.getDefualtFoes(), false, this);

        this.add(bodyPanel, BorderLayout.CENTER);
        this.add(infoPanel, BorderLayout.NORTH);
        this.add(leftWarriorIndicatiorPanel, BorderLayout.WEST);
        this.add(rightWarriorIndicatiorPanel, BorderLayout.EAST);
    }

    @Override
    public void initialize() {

    }
}


class BattleScreenPanelStaticData extends JFrame {


    public static final int ELEMENT_GAP = MenuScreenPanel.ELEMENT_GAP;
    public static final int PREFFERED_AVATAR_SIZE = MenuScreenPanel.PREFFERED_AVATAR_SIZE;
    public static final int PREFFERED_ELEMENT_ICON_SIZE = MenuScreenPanel.PREFFERED_ELEMENT_ICON_SIZE;

    public static final int WARRIOR_INDICATOR_WIDTH = 100;
    public static final int WARRIOR_INDICATOR_HEIGHT = 50;
    public static final int WARRIOR_INDICATOR_PANEL_HEIGHT = WARRIOR_INDICATOR_HEIGHT * 10;
    public static final Dimension WARRIOR_INDICATOR_PANEL_DIMENSION = new Dimension(WARRIOR_INDICATOR_WIDTH, WARRIOR_INDICATOR_PANEL_HEIGHT);
    public static final Dimension WARRIOR_INDICATOR_ELEMENT_DIMENSION = new Dimension(WARRIOR_INDICATOR_WIDTH, WARRIOR_INDICATOR_HEIGHT);

    public static final int BODY_PANEL_WIDTH = 800;

    public static final Dimension BODY_PANEL_DIMENSION = new Dimension(BODY_PANEL_WIDTH, WARRIOR_INDICATOR_PANEL_HEIGHT);

    public static final int INFO_PANEL_WIDTH = BODY_PANEL_WIDTH + 2 * WARRIOR_INDICATOR_WIDTH;
    public static final int INFO_PANEL_HEIGHT = 100;

    public static final Dimension INFO_PANEL_DIMENSION = new Dimension(INFO_PANEL_WIDTH, INFO_PANEL_HEIGHT);

    public static final int BATTLE_SCREEN_PANEL_WIDTH = INFO_PANEL_WIDTH;
    public static final int BATTLE_SCREEN_PANEL_HEIGHT = INFO_PANEL_HEIGHT + WARRIOR_INDICATOR_PANEL_HEIGHT;

    public static Dimension BATTLE_SCREEN_PANEL_DIMENTION = new Dimension(BATTLE_SCREEN_PANEL_WIDTH, BATTLE_SCREEN_PANEL_HEIGHT);
}

class BodyPanel extends MenuScreenPanel {

    public static int DOWN_AND_SIDES_GAP = 50;
    public static int WARRIOR_IMAGES_SIZE = 300;


    private Warrior leftWarrior;
    private Warrior rightWarrior;


    private CustomizedJImage leftWarriorImage;
    private CustomizedJImage rightWarriorImage;



    public BodyPanel(Warrior leftWarrior, Warrior rightWarrior) {

        this.leftWarrior = leftWarrior;
        this.rightWarrior = rightWarrior;


        leftWarriorImage = new CustomizedJImage(leftWarrior.getImage(), WARRIOR_IMAGES_SIZE, WARRIOR_IMAGES_SIZE);
        rightWarriorImage = new CustomizedJImage(rightWarrior.getImage(), WARRIOR_IMAGES_SIZE, WARRIOR_IMAGES_SIZE);


        this.setLayout(null);
        this.setPreferredSize(BattleScreenPanelStaticData.BODY_PANEL_DIMENSION);
        this.setBackground(ChildrenOfTime.GREY);
        initializePanel();
    }


    private void initializePanel() {

        this.add(leftWarriorImage);
        this.add(rightWarriorImage);

        leftWarriorImage.setLocation(DOWN_AND_SIDES_GAP, BattleScreenPanelStaticData.WARRIOR_INDICATOR_PANEL_HEIGHT - WARRIOR_IMAGES_SIZE);
        rightWarriorImage.setLocation(BattleScreenPanelStaticData.BODY_PANEL_WIDTH - DOWN_AND_SIDES_GAP - WARRIOR_IMAGES_SIZE, BattleScreenPanelStaticData.WARRIOR_INDICATOR_PANEL_HEIGHT - WARRIOR_IMAGES_SIZE);


    }

    @Override
    public void initialize() {

    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(ChildrenOfTime.GREY);
        g2d.fillRect(0, 0, BattleScreenPanelStaticData.BODY_PANEL_WIDTH, BattleScreenPanelStaticData.WARRIOR_INDICATOR_PANEL_HEIGHT);
        g2d.drawImage(GameEngine.DEFAULT_TOOLKIT.getImage("src/ui/background/body.png"), 0, 0, this);

    }
}

class WarriorChoosingPanel extends MenuScreenPanel {

    private ArrayList<Warrior> selectedWarriors;
    private BattleScreenPanel battleScreenPanel;

    private int maxNumberOfChoosableWarriors;


    public WarriorChoosingPanel(BattleScreenPanel battleScreenPanel, int maximumNumber, ArrayList<Warrior> selectedWarriors) {

        this.selectedWarriors = selectedWarriors;
        this.maxNumberOfChoosableWarriors = maximumNumber;
        this.battleScreenPanel = battleScreenPanel;
        this.setLayout(null);
        this.setPreferredSize(BattleScreenPanelStaticData.BODY_PANEL_DIMENSION);
        this.setBackground(ChildrenOfTime.GREY);
        doOperations();
    }

    private void doOperations() {
        MouseListener listener = new MouseClickListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
//                if ()
                    selectedWarriors.add(((WarriorIndicatorElement) e.getComponent()).getWarrior());
                System.out.println(selectedWarriors.size());
                System.out.println(((WarriorIndicatorElement) e.getComponent()).getWarrior().getName());
                //TODO FIX FIX FIX FIX FIX FIX
            }
        };

        for (Component component : battleScreenPanel.getLeftWarriorIndicatiorPanel().getComponents()) {
            component.addMouseListener(listener);
        }

        for (Component component : battleScreenPanel.getRightWarriorIndicatiorPanel().getComponents()) {
            component.addMouseListener(listener);
        }
        battleScreenPanel.getLeftWarriorIndicatiorPanel().addMouseListener(listener);
        battleScreenPanel.getRightWarriorIndicatiorPanel().addMouseListener(listener);
        battleScreenPanel.addMouseListener(listener);
    }


    @Override
    public void initialize() {
        CustomizedJButton okButton = new CustomizedJButton("OK");
        okButton.setLocation((BattleScreenPanelStaticData.BODY_PANEL_WIDTH - CustomizedJButton.BUTTON_WIDTH) / 2,
                (BattleScreenPanelStaticData.WARRIOR_INDICATOR_PANEL_HEIGHT - CustomizedJButton.BUTTON_HEIGHT) / 2);
        this.add(okButton);

        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (Component component : battleScreenPanel.getLeftWarriorIndicatiorPanel().getComponents()) {
                    component.addMouseListener(new MouseClickListener() {
                        @Override

                        public void mouseClicked(MouseEvent e) {
                            if (!battleScreenPanel.getLeftWarriorIndicatiorPanel().isPlayable()) {
                                GUIUtils.showNotification("You cant play with your Rival team's Warriors!", NotificationType.ERROR);
                            } else {
                                Warrior target = ((WarriorIndicatorElement) e.getComponent()).getWarrior();
                                GUIUtils.showNotification("Warrior " + target.getName() + " (" + target.getInfo().getClassName() + ") " + target.getId() + " has been selected. Choose the operation", NotificationType.NORMAL);
                                new BattleActionChooserDialog(target, battleScreenPanel);
                            }
                            System.out.println(e.getComponent().getClass());
                        }
                    });
                }
            }
        });
        emerge();
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(ChildrenOfTime.GREY);
        g2d.fillRect(0, 0, BattleScreenPanelStaticData.BODY_PANEL_WIDTH, BattleScreenPanelStaticData.WARRIOR_INDICATOR_PANEL_HEIGHT);
    }
}
class InfoIndicatorPanel extends JPanel {

    public static final int BORDER_GAP = 20;

    public static final int PAUSE_BUTTON_WIDTH = 60;
    public static final int PAUSE_BUTTON_HEIGHT = BattleScreenPanelStaticData.INFO_PANEL_HEIGHT - 2 * BORDER_GAP;
    public static final Dimension PAUSE_BUTTON_DIMENSION = new Dimension(PAUSE_BUTTON_WIDTH, PAUSE_BUTTON_HEIGHT);

    public static final int USER_AVATAR_DIMENTION = BattleScreenPanelStaticData.INFO_PANEL_HEIGHT - 2 * BORDER_GAP;

    public static final int MAX_BAR_WIDTH = (BattleScreenPanelStaticData.INFO_PANEL_WIDTH - PAUSE_BUTTON_WIDTH) / 2 - 3 * BORDER_GAP - USER_AVATAR_DIMENTION;
    public static final int BAR_HEIGHT = (BattleScreenPanelStaticData.INFO_PANEL_HEIGHT - 4 * BORDER_GAP) / 3;


    private Warrior leftSideWarrior;
    private Warrior rightSideWarrior;
    private JButton pauseButton = new CustomizedJButton("||");


    private CustomizedJImage leftUserAvatar = new CustomizedJImage("src/ui/icon/avatar.png", USER_AVATAR_DIMENTION, USER_AVATAR_DIMENTION);
    private CustomizedJImage rightUserAvatar = new CustomizedJImage("src/ui/icon/avatar.png", USER_AVATAR_DIMENTION, USER_AVATAR_DIMENTION);


    private double leftSideWarriorHealthAR;
    private double leftSideWarriorMagicAR;
    private double leftSideWarriorEnergyAR;
    private double rightSideWarriorHealthAR;
    private double rightSideWarriorEnergyAR;
    private double rightSideWarriorMagicAR;

    private int leftSideWarriorMaxHealth = 100;
    private int leftSideWarriorMaxMagic = 100;
    private int leftSideWarriorMaxEnergy = 100;
    private int rightSideWarriorMaxHealth = 100;
    private int rightSideWarriorMaxMagic = 100;
    private int rightSideWarriorMaxEnergy = 100;

    private int leftSideWarriorCurrentHealth = 100;
    private int leftSideWarriorCurrentMagic = 10;
    private int leftSideWarriorCurrentEnergy = 90;
    private int rightSideWarriorCurrentHealth = 100;
    private int rightSideWarriorCurrentMagic = 30;
    private int rightSideWarriorCurrentEnergy = 50;

    public InfoIndicatorPanel(Warrior leftSideWarrior, Warrior rightSideWarrior) {
        this.leftSideWarrior = leftSideWarrior;
        this.rightSideWarrior = rightSideWarrior;


        leftSideWarriorMaxHealth = leftSideWarrior.getMaxHealth();
        leftSideWarriorMaxMagic = leftSideWarrior.getMaxMagic();
        leftSideWarriorMaxEnergy = leftSideWarrior.getInfo().getInitialEP();
        rightSideWarriorMaxHealth = rightSideWarrior.getMaxHealth();
        rightSideWarriorMaxMagic = rightSideWarrior.getMaxMagic();
        rightSideWarriorMaxEnergy = rightSideWarrior.getInfo().getInitialEP();


        this.setLayout(null);
        this.setPreferredSize(BattleScreenPanelStaticData.INFO_PANEL_DIMENSION);
        this.setBackground(ChildrenOfTime.GREY);
        initializePanel();
    }

    private void calculateARs() {


        leftSideWarriorCurrentHealth = leftSideWarrior.getCurrentHealth();
        leftSideWarriorCurrentMagic = leftSideWarrior.getCurrentMagic();
        leftSideWarriorCurrentEnergy = leftSideWarrior.getCurrentEP();
        rightSideWarriorCurrentHealth = rightSideWarrior.getCurrentHealth();
        rightSideWarriorCurrentMagic = rightSideWarrior.getCurrentMagic();
        rightSideWarriorCurrentEnergy = rightSideWarrior.getCurrentEP();


        leftSideWarriorHealthAR = ((double) leftSideWarriorCurrentHealth) / ((double) leftSideWarriorMaxHealth);
        leftSideWarriorMagicAR = ((double) leftSideWarriorCurrentMagic) / ((double) leftSideWarriorMaxMagic);
        leftSideWarriorEnergyAR = ((double) leftSideWarriorCurrentEnergy) / ((double) leftSideWarriorMaxEnergy);

        rightSideWarriorHealthAR = ((double) rightSideWarriorCurrentHealth) / ((double) rightSideWarriorMaxHealth);
        rightSideWarriorMagicAR = ((double) rightSideWarriorCurrentMagic) / ((double) rightSideWarriorMaxMagic);
        rightSideWarriorEnergyAR = ((double) rightSideWarriorCurrentEnergy) / ((double) rightSideWarriorMaxEnergy);
    }

    private void initializePanel() {

        pauseButton.setSize(PAUSE_BUTTON_DIMENSION);

        Font pauseButtonFont = new Font(null, Font.BOLD, 28);
        pauseButton.setFont(pauseButtonFont);
        pauseButton.setVerticalAlignment(SwingConstants.CENTER);
        pauseButton.setHorizontalAlignment(SwingConstants.CENTER);
        pauseButton.setForeground(Color.red);

        leftUserAvatar.setPreferredSize(new Dimension(USER_AVATAR_DIMENTION, USER_AVATAR_DIMENTION));
        rightUserAvatar.setPreferredSize(new Dimension(USER_AVATAR_DIMENTION, USER_AVATAR_DIMENTION));

        this.add(pauseButton);
        this.add(leftUserAvatar);
        this.add(rightUserAvatar);


        pauseButton.setLocation(3 * BORDER_GAP + USER_AVATAR_DIMENTION + MAX_BAR_WIDTH, BORDER_GAP);
        leftUserAvatar.setLocation(BORDER_GAP, BORDER_GAP);
        rightUserAvatar.setLocation(BattleScreenPanelStaticData.INFO_PANEL_WIDTH - BORDER_GAP - USER_AVATAR_DIMENTION, BORDER_GAP);

        pauseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BattleScreenPanel battleScreenPanel = (BattleScreenPanel) ChildrenOfTime.frame.getContentPane();
                ChildrenOfTime.changeContentPane(new BattleScreenPanelPause(battleScreenPanel));

            }
        }); //TODO IMPLEMENT
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(ChildrenOfTime.GREY);
        g2d.fillRect(0, 0, BattleScreenPanelStaticData.INFO_PANEL_WIDTH, BattleScreenPanelStaticData.WARRIOR_INDICATOR_PANEL_HEIGHT);
        g2d.fillRect(0, 0, BattleScreenPanelStaticData.INFO_PANEL_WIDTH, BattleScreenPanelStaticData.INFO_PANEL_HEIGHT);
        g2d.drawImage(GameEngine.DEFAULT_TOOLKIT.getImage("src/ui/background/top.png"), 0, 0, this);

        calculateARs();

        g2d.setColor(Color.red);
        g2d.fillRect(2 * BORDER_GAP + USER_AVATAR_DIMENTION, BORDER_GAP, (int) (leftSideWarriorHealthAR * MAX_BAR_WIDTH), BAR_HEIGHT);
        g2d.setColor(Color.blue);
        g2d.fillRect(2 * BORDER_GAP + USER_AVATAR_DIMENTION, 2 * BORDER_GAP + BAR_HEIGHT, (int) (leftSideWarriorMagicAR * MAX_BAR_WIDTH), BAR_HEIGHT);
        g2d.setColor(Color.yellow);
        g2d.fillRect(2 * BORDER_GAP + USER_AVATAR_DIMENTION, 3 * BORDER_GAP + 2 * BAR_HEIGHT, (int) (leftSideWarriorEnergyAR * MAX_BAR_WIDTH), BAR_HEIGHT);


        g2d.setColor(Color.red);
        g2d.fillRect(4 * BORDER_GAP + USER_AVATAR_DIMENTION + MAX_BAR_WIDTH + PAUSE_BUTTON_WIDTH + (int) ((1 - rightSideWarriorHealthAR) * MAX_BAR_WIDTH), BORDER_GAP, (int) (rightSideWarriorHealthAR * MAX_BAR_WIDTH), BAR_HEIGHT);
        g2d.setColor(Color.blue);
        g2d.fillRect(4 * BORDER_GAP + USER_AVATAR_DIMENTION + MAX_BAR_WIDTH + PAUSE_BUTTON_WIDTH + (int) ((1 - rightSideWarriorMagicAR) * MAX_BAR_WIDTH), 2 * BORDER_GAP + BAR_HEIGHT, (int) (rightSideWarriorMagicAR * MAX_BAR_WIDTH), BAR_HEIGHT);
        g2d.setColor(Color.yellow);
        g2d.fillRect(4 * BORDER_GAP + USER_AVATAR_DIMENTION + MAX_BAR_WIDTH + PAUSE_BUTTON_WIDTH + (int) ((1 - rightSideWarriorEnergyAR) * MAX_BAR_WIDTH), 3 * BORDER_GAP + 2 * BAR_HEIGHT, (int) (rightSideWarriorEnergyAR * MAX_BAR_WIDTH), BAR_HEIGHT);
    }
}
class WarriorIndicatorPanel extends JPanel {

    private ArrayList<Warrior> warriors;
    private boolean playable = true;
    private BattleScreenPanel battleScreenPanel;

    public WarriorIndicatorPanel(ArrayList<Warrior> warriors, boolean playable, BattleScreenPanel battleScreenPanel) {

        this.battleScreenPanel = battleScreenPanel;
        this.playable = playable;
        this.warriors = warriors;
        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        this.setPreferredSize(BattleScreenPanelStaticData.WARRIOR_INDICATOR_PANEL_DIMENSION);
        this.setBackground(ChildrenOfTime.GREY);
        createAndShowPanel();
    }

    public boolean isPlayable() {
        return playable;
    }

    public void setPlayable(boolean playable) {
        playable = playable;
    }
    private void createAndShowPanel() {

        MouseListener listener = new MouseClickListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (!playable) {
                    GUIUtils.showNotification("You cant play with your Rival team's Warriors!", NotificationType.ERROR);
                } else {
                    Warrior target = ((WarriorIndicatorElement) e.getComponent()).getWarrior();
                    GUIUtils.showNotification("Warrior " + target.getName() + " (" + target.getInfo().getClassName() + ") " + target.getId() + " has been selected. Choose the operation", NotificationType.NORMAL);
                    new BattleActionChooserDialog(target, battleScreenPanel);
                }
                System.out.println(e.getComponent().getClass());
            }
        };
        this.addMouseListener(listener);
        for (Warrior warrior : warriors) {
            WarriorIndicatorElement toAdd = new WarriorIndicatorElement(warrior);
            toAdd.addMouseListener(listener);
            this.add(toAdd);
        }
    }
}
class WarriorIndicatorElement extends JPanel {


    public static final int BORDER_GAP = 10;
    public static final int ICON_DIMENSION = BattleScreenPanelStaticData.WARRIOR_INDICATOR_HEIGHT - 2 * BORDER_GAP;
    public static final int MAX_BAR_WIDTH = BattleScreenPanelStaticData.WARRIOR_INDICATOR_WIDTH - 2 * BORDER_GAP - ICON_DIMENSION;
    public static final int BAR_HEIGHT = 2;


    private Warrior warrior;

    public Warrior getWarrior() {
        return warrior;
    }

    private ImageIcon icon;/* = GUIUtils.getScaledImageByFilePath("src/ui/Children Of Time Art Assets/COT (33).png",ICON_DIMENSION,ICON_DIMENSION,Image.SCALE_DEFAULT);*/
    private int maxHealth = 100;
    private int maxMagic = 100;
    private int maxEnergy = 100;
    private int currentHealth = 70;
    private int currentMagic = 65;
    private int currentEnergy = 25;

    private double healthAR;
    private double magicAR;
    private double energyAR;





    public WarriorIndicatorElement(Warrior warrior) {
        this.warrior = warrior;
        this.setPreferredSize(BattleScreenPanelStaticData.WARRIOR_INDICATOR_ELEMENT_DIMENSION);
        this.icon = warrior.getImage();

        maxHealth = warrior.getMaxHealth();
        maxMagic = warrior.getMaxMagic();
        maxEnergy = warrior.getInfo().getInitialEP();

    }

    public WarriorIndicatorElement() {
        this.setPreferredSize(BattleScreenPanelStaticData.WARRIOR_INDICATOR_ELEMENT_DIMENSION);

        maxHealth = warrior.getMaxHealth();
        maxMagic = warrior.getMaxMagic();
        maxEnergy = warrior.getInfo().getInitialEP();

    }


    public void calculateARs() {

        currentHealth = warrior.getCurrentHealth();
        currentMagic = warrior.getCurrentMagic();
        currentEnergy = warrior.getCurrentEP();

        healthAR = ((double) currentHealth) / ((double) maxHealth);
        magicAR = ((double) currentMagic) / ((double) maxMagic);
        energyAR = ((double) currentEnergy) / ((double) maxEnergy);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(ChildrenOfTime.GREY);
        g2d.fillRect(0, 0, BattleScreenPanelStaticData.WARRIOR_INDICATOR_WIDTH, BattleScreenPanelStaticData.WARRIOR_INDICATOR_HEIGHT);

        g2d.drawImage(icon.getImage(), BattleScreenPanelStaticData.WARRIOR_INDICATOR_WIDTH - BORDER_GAP - ICON_DIMENSION,
                BORDER_GAP, ICON_DIMENSION, ICON_DIMENSION, this);

        calculateARs();

        g2d.setColor(Color.red);
        g2d.fillRect(BORDER_GAP, BORDER_GAP, (int) (healthAR * MAX_BAR_WIDTH), BAR_HEIGHT);
        g2d.setColor(Color.blue);
        g2d.fillRect(BORDER_GAP, 2 * BORDER_GAP + BAR_HEIGHT, (int) (magicAR * MAX_BAR_WIDTH), BAR_HEIGHT);
        g2d.setColor(Color.yellow);
        g2d.fillRect(BORDER_GAP, 3 * BORDER_GAP + 2 * BAR_HEIGHT, (int) (energyAR * MAX_BAR_WIDTH), BAR_HEIGHT);
    }


    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.add(new BattleScreenPanel());

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
