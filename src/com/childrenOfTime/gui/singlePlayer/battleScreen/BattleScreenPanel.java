package com.childrenOfTime.gui.singlePlayer.battleScreen;

import com.childrenOfTime.controller.GameEngine;
import com.childrenOfTime.gui.announcementPanels.GameOverAnnouncementPanel;
import com.childrenOfTime.gui.announcementPanels.ModalAnnouncer;
import com.childrenOfTime.gui.customizedElements.CustomizedJButton;
import com.childrenOfTime.gui.customizedElements.CustomizedJImage;
import com.childrenOfTime.gui.customizedElements.CustomizedJLabel;
import com.childrenOfTime.gui.customizedElements.MenuScreenPanel;
import com.childrenOfTime.gui.customizedListeners.MouseClickListener;
import com.childrenOfTime.gui.fillForms.BattleActionChooserDialog;
import com.childrenOfTime.gui.notification.NotificationType;
import com.childrenOfTime.gui.singlePlayer.BattleScreenPanelPause;
import com.childrenOfTime.model.*;
import com.childrenOfTime.model.Equip.AbilComps.Ability;
import com.childrenOfTime.model.Equip.ItemComps.Item;
import com.childrenOfTime.model.Warriors.ActionType;
import com.childrenOfTime.model.Warriors.Warrior;
import com.childrenOfTime.utilities.GUIUtils;
import com.sun.istack.internal.Nullable;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.LinkedList;

public class BattleScreenPanel extends JPanel {

    public static BattleScreenPanel lastState;

    public static BattleScreenPanel instance;

    public static boolean isPlayersTurn = true;

    private Battle battle;
    private ArrayList<Warrior> warriors;

    private Player userPlayer;
    private ArtificialBrain AI;

    public Player getComputerPlayer() {
        return computerPlayer;
    }

    private Player computerPlayer;

    public BodyPanel getBodyPanel() {
        return bodyPanel;
    }

    private BodyPanel bodyPanel;

    private InfoIndicatorPanel infoPanel;
    private WarriorIndicatorPanel leftWarriorIndicatiorPanel;
    private WarriorIndicatorPanel rightWarriorIndicatiorPanel;



    public Player getUserPlayer() {
        return userPlayer;
    }

    public WarriorIndicatorPanel getRightWarriorIndicatiorPanel() {
        return rightWarriorIndicatiorPanel;
    }
    public WarriorIndicatorPanel getLeftWarriorIndicatiorPanel() {
        return leftWarriorIndicatiorPanel;
    }


    public ArrayList<Warrior> warriorChoosingWizard(int maximumNumber, Warrior executingWarrior, ActionType executionType, @Nullable Ability castedAbility, @Nullable Item usedItem) {
        ArrayList<Warrior> chosenWarriors = new ArrayList<>();
        changeBodyPane(new WarriorChoosingPanel(this, maximumNumber, chosenWarriors, executingWarrior, executionType, castedAbility, usedItem));
        return chosenWarriors;
    }

    public void changeBodyPane(JPanel newBodyPane) {
        BodyPanel currentBodyPane = bodyPanel;
        BodyPanel.lastState = bodyPanel;

        BorderLayout layout = (BorderLayout) instance.getLayout();

        instance.remove(layout.getLayoutComponent(BorderLayout.CENTER));
        add(newBodyPane, BorderLayout.CENTER);
        ChildrenOfTime.frame.pack();
        ChildrenOfTime.frame.setLocationRelativeTo(null);
        ChildrenOfTime.frame.setVisible(true);
        newBodyPane.repaint();
        newBodyPane.requestFocus();
    }

    public void revertChangeContentPane() {

        BorderLayout layout = (BorderLayout) instance.getLayout();

        instance.remove(layout.getLayoutComponent(BorderLayout.CENTER));

        add(BodyPanel.lastState, BorderLayout.CENTER);
        ChildrenOfTime.frame.pack();
        ChildrenOfTime.frame.setLocationRelativeTo(null);
        ChildrenOfTime.frame.setVisible(true);
        BodyPanel.lastState.repaint();
        BodyPanel.lastState.requestFocus();
    }

    public BattleScreenPanel(Battle battle, ArrayList<Warrior> warriors, Player userPlayer) {

        instance = this;
        this.battle = battle;
        this.warriors = warriors;
        this.userPlayer = userPlayer;
        this.computerPlayer = new Player(battle.getDefualtFoes(), "Computer", PlayerType.Computer);
        this.AI = new ArtificialBrain(computerPlayer, userPlayer);
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

    public void playAITurn() {
        LinkedList<Act> aiActs = AI.playATurn();

        for (Act aiAct : aiActs) {
            bodyPanel.setRightWarriorImage(aiAct.getPerformer().getImage());
            AI.doTheAct(aiAct);
        }
    }

}

class BattleScreenPanelStaticData {


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

class BodyPanel extends JPanel {


    public static BodyPanel lastState;
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

    public void setLeftWarriorImage(ImageIcon image) {
        leftWarriorImage.setIcon(image);
    }

    public void setRightWarriorImage(ImageIcon image) {
        rightWarriorImage.setIcon(image);
    }


    private void initializePanel() {

        this.add(leftWarriorImage);
        this.add(rightWarriorImage);


        leftWarriorImage.setLocation(DOWN_AND_SIDES_GAP, BattleScreenPanelStaticData.WARRIOR_INDICATOR_PANEL_HEIGHT - WARRIOR_IMAGES_SIZE);
        rightWarriorImage.setLocation(BattleScreenPanelStaticData.BODY_PANEL_WIDTH - DOWN_AND_SIDES_GAP - WARRIOR_IMAGES_SIZE, BattleScreenPanelStaticData.WARRIOR_INDICATOR_PANEL_HEIGHT - WARRIOR_IMAGES_SIZE);

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

class WarriorChoosingPanel extends JPanel {


    private Warrior executingWarrior;
    private ActionType executionType;


    private ArrayList<Warrior> selectedWarriors;
    private BattleScreenPanel battleScreenPanel;

    private int maxNumberOfChoosableWarriors;
    private CustomizedJLabel counterLabel;

    private Item usedItem = null;
    private Ability castedAbility = null;


    public WarriorChoosingPanel(BattleScreenPanel battleScreenPanel, int maximumNumber, ArrayList<Warrior> selectedWarriors,
                                Warrior executingWarrior, ActionType executionType, @Nullable Ability castedAbility, @Nullable Item usedItem) {

        initialize();
        this.castedAbility = castedAbility;
        this.usedItem = usedItem;
        this.executionType = executionType;
        this.executingWarrior = executingWarrior;
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

                Warrior selectedWarrior = ((WarriorIndicatorElement) e.getComponent()).getWarrior();
                boolean alredyExisting = false;

                for (Warrior warrior : selectedWarriors) {
                    if (warrior.equals(selectedWarrior) && warrior.getId() == selectedWarrior.getId())
                        alredyExisting = true;
                }

                if (alredyExisting) {
                    GUIUtils.showNotification("this warrior: " + selectedWarrior.getName() + " (" + selectedWarrior.getInfo().getClassName() + ") "
                            + selectedWarrior.getId() + "is already chosen! choose another one.", NotificationType.ERROR);
                } else {
                    if (selectedWarriors.size() >= maxNumberOfChoosableWarriors) {
                        GUIUtils.showNotification("you cannot select more than " + maxNumberOfChoosableWarriors + " targets for this operation", NotificationType.ERROR);
                    } else {
                        selectedWarriors.add(selectedWarrior);
                        System.out.println(selectedWarriors.size());
                        System.out.println(((WarriorIndicatorElement) e.getComponent()).getWarrior().getName());

                    }
                }
            }
        };

        for (Component component : battleScreenPanel.getLeftWarriorIndicatiorPanel().getComponents()) {

            for (MouseListener mouseListener : component.getMouseListeners()) {
                component.removeMouseListener(mouseListener);
            }
            component.addMouseListener(listener);
        }

        for (Component component : battleScreenPanel.getRightWarriorIndicatiorPanel().getComponents()) {
            for (MouseListener mouseListener : component.getMouseListeners()) {
                component.removeMouseListener(mouseListener);
            }
            component.addMouseListener(listener);
        }


        for (MouseListener mouseListener : battleScreenPanel.getLeftWarriorIndicatiorPanel().getMouseListeners()) {
            battleScreenPanel.getLeftWarriorIndicatiorPanel().removeMouseListener(mouseListener);
        }
        battleScreenPanel.getLeftWarriorIndicatiorPanel().addMouseListener(listener);


        for (MouseListener mouseListener : battleScreenPanel.getRightWarriorIndicatiorPanel().getMouseListeners()) {
            battleScreenPanel.getRightWarriorIndicatiorPanel().removeMouseListener(mouseListener);
        }
        battleScreenPanel.getRightWarriorIndicatiorPanel().addMouseListener(listener);


        for (MouseListener mouseListener : battleScreenPanel.getMouseListeners()) {
            battleScreenPanel.removeMouseListener(mouseListener);
        }
        battleScreenPanel.addMouseListener(listener);
    }

    public void initialize() {
        CustomizedJButton okButton = new CustomizedJButton("OK");
        okButton.setLocation((BattleScreenPanelStaticData.BODY_PANEL_WIDTH - CustomizedJButton.BUTTON_WIDTH) / 2,
                (BattleScreenPanelStaticData.WARRIOR_INDICATOR_PANEL_HEIGHT - CustomizedJButton.BUTTON_HEIGHT) / 2);
        this.add(okButton);


        counterLabel = new CustomizedJLabel("selected: no warriors");
        counterLabel.setLocation((BattleScreenPanelStaticData.BODY_PANEL_WIDTH - CustomizedJLabel.LABEL_WIDTH) / 2,
                (BattleScreenPanelStaticData.WARRIOR_INDICATOR_PANEL_HEIGHT - CustomizedJButton.BUTTON_HEIGHT) / 2 - CustomizedJLabel.LABEL_HEIGHT - MenuScreenPanel.ELEMENT_GAP);
        this.add(counterLabel);

        //TODO may have some unfinished business here
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                /////////////////////////////////////////////////////////////////////////////////
                /////////////////////resetting the listeners/////////////////////////////////////
                /////////////////////////////////////////////////////////////////////////////////

                MouseClickListener resetedListener = new MouseClickListener() {
                    @Override

                    public void mouseClicked(MouseEvent e) {

                        WarriorIndicatorElement targetElement = (WarriorIndicatorElement) e.getComponent();

                        if (!targetElement.isPlayable()) {
                            GUIUtils.showNotification("You cant play with your Rival team's Warriors!", NotificationType.ERROR);
                        } else {
                            Warrior target = ((WarriorIndicatorElement) e.getComponent()).getWarrior();

                            BattleScreenPanel.instance.getBodyPanel().setLeftWarriorImage(target.getImage());

                            GUIUtils.showNotification("Warrior " + target.getName() + " (" + target.getInfo().getClassName() + ") " + target.getId() + " has been selected. Choose the operation", NotificationType.NORMAL);
                            new BattleActionChooserDialog(target, battleScreenPanel);
                        }
                        System.out.println(e.getComponent().getClass());


                    }
                };

                for (Component component : battleScreenPanel.getLeftWarriorIndicatiorPanel().getComponents()) {
                    ///////////////////////////////
                    for (MouseListener mouseListener : component.getMouseListeners()) {
                        component.removeMouseListener(mouseListener);
                    }
                    /////////////////////
                    component.addMouseListener(resetedListener);
                }
                for (Component component : battleScreenPanel.getRightWarriorIndicatiorPanel().getComponents()) {
                    ///////////////////////////////
                    for (MouseListener mouseListener : component.getMouseListeners()) {
                        component.removeMouseListener(mouseListener);
                    }
                    /////////////////////
                    component.addMouseListener(resetedListener);
                }

                /////////////////////////////////////////////////////////////////////////////////
                /////////////////////////////////////////////////////////////////////////////////
                /////////////////////////////////////////////////////////////////////////////////


                /////////////////////////////////////////////////////////////////////////////////
                /////////////////////EXECUTING THE OPERATION/////////////////////////////////////
                /////////////////////////////////////////////////////////////////////////////////


                ArrayList<Warrior> allEnemies = new ArrayList<Warrior>();


                for (Component component : battleScreenPanel.getRightWarriorIndicatiorPanel().getComponents()) {
                    WarriorIndicatorElement element = (WarriorIndicatorElement) component;
                    allEnemies.add(element.getWarrior());
                }

                switch (executionType) {
                    case Attack:
                        battleScreenPanel.getUserPlayer().giveAttack(executingWarrior, selectedWarriors.toArray(new Warrior[selectedWarriors.size()]), allEnemies);
                        break;
                    case BurnEP:
                        executingWarrior.burnEP(selectedWarriors.toArray(new Warrior[selectedWarriors.size()]));
                        break;
                    case AbilityCast:
                        battleScreenPanel.getUserPlayer().castAbility(executingWarrior, castedAbility, allEnemies, selectedWarriors.toArray(new Warrior[selectedWarriors.size()]));
                        break;
                    case UseItem:
                        battleScreenPanel.getUserPlayer().useItem(executingWarrior, usedItem, allEnemies, selectedWarriors.toArray(new Warrior[selectedWarriors.size()]));
                        break;
                }

                /////////////////////////////////////////////////////////////////////////////////
                /////////////////////////////////////////////////////////////////////////////////
                /////////////////////////////////////////////////////////////////////////////////


                /////////////////////////////////////////////////////////////////////////////////
                /////////////////////CLOSE WARRIOR CHOOSING DIALOG///////////////////////////////
                /////////////////////////////////////////////////////////////////////////////////

                battleScreenPanel.revertChangeContentPane();
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        counterLabel.setText("selected: " + selectedWarriors.size() + "/" + maxNumberOfChoosableWarriors + " warriors");
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
    private JButton pauseButton = new CustomizedJButton("40");


    private Timer turnTimer = new Timer(1000, e -> {

        if (pauseButton.getText().equals("0")) {

            BattleScreenPanel.instance.playAITurn();
            pauseButton.setText("40");
            BattleScreenPanel.isPlayersTurn = !BattleScreenPanel.isPlayersTurn;
        } else {
            if (BattleScreenPanel.instance.getUserPlayer().isDefeated()) {
                new ModalAnnouncer(new GameOverAnnouncementPanel());
                ((ModalAnnouncer) this.getParent()).dispose();
            }
            if (BattleScreenPanel.instance.getComputerPlayer().isDefeated()) {
                GUIUtils.showNotification("You Won!", NotificationType.GOOD);
                ((ModalAnnouncer) this.getParent()).dispose();
            }
            pauseButton.setText(String.valueOf(Integer.parseInt(pauseButton.getText()) - 1));
            BattleScreenPanel.instance.repaint();
        }
    });


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
        turnTimer.start();
        pauseButton.setSize(PAUSE_BUTTON_DIMENSION);

        Font pauseButtonFont = new Font(null, Font.BOLD, 12);
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
//                BattleScreenPanel battleScreenPanel = (BattleScreenPanel) ChildrenOfTime.frame.getContentPane();
//                BattleScreenPanel.lastState = battleScreenPanel;
                new ModalAnnouncer(new BattleScreenPanelPause(BattleScreenPanel.instance));
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
        this.playable = playable;
    }
    private void createAndShowPanel() {

        MouseListener listener = new MouseClickListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

                WarriorIndicatorElement targetElement = (WarriorIndicatorElement) e.getComponent();

                if (!targetElement.isPlayable()) {
                    GUIUtils.showNotification("You cant play with your Rival team's Warriors!", NotificationType.ERROR);
                } else {
                    Warrior target = ((WarriorIndicatorElement) e.getComponent()).getWarrior();
                    GUIUtils.showNotification("Warrior " + target.getName() + " (" + target.getInfo().getClassName() + ") " + target.getId() + " has been selected. Choose the operation", NotificationType.NORMAL);
                    new BattleActionChooserDialog(target, battleScreenPanel);
                }
                System.out.println(e.getComponent().getClass());
            }
        };

        for (MouseListener mouseListener : this.getMouseListeners()) {
            this.removeMouseListener(mouseListener);
        }
        this.addMouseListener(listener);
        for (Warrior warrior : warriors) {
            WarriorIndicatorElement toAdd = new WarriorIndicatorElement(warrior, playable);
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


    private boolean playable;

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

    private Warrior warrior;


    public Warrior getWarrior() {
        return warrior;
    }

    public WarriorIndicatorElement(Warrior warrior, boolean playable) {

        this.playable = playable;
        this.warrior = warrior;
        this.setPreferredSize(BattleScreenPanelStaticData.WARRIOR_INDICATOR_ELEMENT_DIMENSION);
        this.icon = warrior.getImage();

        maxHealth = warrior.getMaxHealth();
        maxMagic = warrior.getMaxMagic();
        maxEnergy = warrior.getInfo().getInitialEP();

    }

    public boolean isPlayable() {
        return playable;
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
//        frame.add(new BattleScreenPanel());

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
