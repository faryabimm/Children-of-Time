package com.childrenOfTime.model;


import com.childrenOfTime.gui.LoadingScreenPanel;
import com.childrenOfTime.gui.MainMenuScreenPanel;
import com.childrenOfTime.gui.customGame.CustomGameLoginPanel;
import com.childrenOfTime.gui.customGame.CustomGameMenuScreenPanel;
import com.childrenOfTime.gui.customizedElements.MenuScreenPanel;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by mohammadmahdi on 5/7/16.
 */
public final class ChildrenOfTime implements Serializable {



    public static final int FPS = 60;
    public static final int PREFERRED_WIDTH = 1000;
    public static final int PREFERRED_HEIGHT = 600;
    public static final Dimension PREFERRED_DIMENSION = new Dimension(PREFERRED_WIDTH, PREFERRED_HEIGHT);
    public static final Color GREY_LIGHT = new Color(127, 127, 127);
    public static final Color GREY = new Color(48, 48, 48);

    public static Font ASFALTO_FONT = null;
    public static Font TIZA_FONT = null;

    public static final MenuScreenPanel MAIN_MENU = new MainMenuScreenPanel();
    public static final MenuScreenPanel SIGN_IN_MENU = new CustomGameLoginPanel();
    public static final MenuScreenPanel CUSTOM_GAME_MENU = new CustomGameMenuScreenPanel();


    static {
        try {
            ASFALTO_FONT = Font.createFont(Font.TRUETYPE_FONT, new File("src/ui/font/asfalto.otf"));
            ASFALTO_FONT = Font.createFont(Font.TRUETYPE_FONT, new File("src/ui/font/tiza.ttf"));
        } catch (FontFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    public static JFrame frame = null;

    private static ChildrenOfTime instance;
    private ArrayList<Player> players = new ArrayList<>();
//    private ArrayList<Battle> battles = new ArrayList<>();
    Boolean firstTime = true;
    
    public static ChildrenOfTime getInstance() {
        if (instance == null) {
            try {
                instance = new ChildrenOfTime();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (FontFormatException e) {
                e.printStackTrace();
            }
        }
        return instance;
    }
    private ChildrenOfTime() throws IOException, FontFormatException {

/*
        ArrayList<Foe> battleFoes = new ArrayList<>();

        battleFoes.add(new Foe("Thug", StrengthOfFoes.Weak, 0));
        battleFoes.add(new Foe("Thug", StrengthOfFoes.Weak, 1));
        battleFoes.add(new Foe("Thug", StrengthOfFoes.Weak, 2));
        battleFoes.add(new Foe("Angel", StrengthOfFoes.Weak, 0));
        String storyTemp = "Battle 1 : \n" +
                "You’ve entered the castle, it takes a while for your eyes to get used to the darkness but\n" +
                "the horrifying halo of your enemies is vaguely visible. Angel’s unsettling presence and\n" +
                "the growling of thugs tell you that your first battle has BEGUN!";
        battles.add(new Battle(storyTemp, new Reward(20, 50), battleFoes));


        battleFoes = new ArrayList<>();
        battleFoes.add(new Foe("Thug", StrengthOfFoes.Able, 0));
        battleFoes.add(new Foe("Thug", StrengthOfFoes.Able, 1));
        battleFoes.add(new Foe("Angel", StrengthOfFoes.Weak, 0));
        battleFoes.add(new Foe("Tank", StrengthOfFoes.Weak, 0));
        storyTemp = "Battle 2 : \n" +
                "As you wander into the hall you realize the surrounding doors can lead your destiny to\n" +
                "something far worse than you expected. You know what’s anticipating you behind the only\n" +
                "open door but there’s no other choice.";
        battles.add(new Battle(storyTemp, new Reward(25, 60), battleFoes));


        battleFoes = new ArrayList<>();
        battleFoes.add(new Foe("Thug", StrengthOfFoes.Able, 0));
        battleFoes.add(new Foe("Thug", StrengthOfFoes.Mighty, 0));
        battleFoes.add(new Foe("Angel", StrengthOfFoes.Able, 0));
        battleFoes.add(new Foe("Tank", StrengthOfFoes.Weak, 0));
        storyTemp = "Battle 3 \n" +
                "The door behind you is shut with a thunderous sound and you progress into the next hall\n" +
                "holding the first key that you’ve found, hoping to seek the second one.";
        battles.add(new Battle(storyTemp, new Reward(30, 70), battleFoes));


        battleFoes = new ArrayList<>();
        battleFoes.add(new Foe("Thug", StrengthOfFoes.Mighty, 0));
        battleFoes.add(new Foe("Thug", StrengthOfFoes.Mighty, 1));
        battleFoes.add(new Foe("Angel", StrengthOfFoes.Able, 0));
        battleFoes.add(new Foe("Tank", StrengthOfFoes.Able, 0));
        battleFoes.add(new Foe("Tank", StrengthOfFoes.Able, 1));
        storyTemp = "Battle 4 : \n" +
                "Running with the second key in your hand, you unlock the door back to the first hall and\n" +
                "use the first key to burst into your most terrifying nightmares.";
        battles.add(new Battle(storyTemp, new Reward(35, 80), battleFoes));


        battleFoes = new ArrayList<>();
        battleFoes.add(new Foe("Final Boss", StrengthOfFoes.Dramatic, 0));
        storyTemp = "Battle 5 : \n " +
                "You feel hopeless and exhausted as you stalk to the final door. What’s behind that door\n" +
                "makes your hearts pound and your spines shake with fear, but you came here to do one\n" +
                "thing and backing down is not an option.";
        battles.add(new Battle(storyTemp, new Reward(0, 0), battleFoes));


        ArrayList<Hero> battleHeros = new ArrayList<>();

        battleHeros.add(new Hero("Meryl", "Supporter", 0));
        battleHeros.add(new Hero("Bolti", "Supporter", 0));
        battleHeros.add(new Hero("Eley", "Fighter", 0));
        battleHeros.add(new Hero("Chrome", "Fighter", 0));
        players.add(new Object(battleHeros));

        Store store = new Store();
        Store.addStore(store);

    }

    public void startSinglePlayerMode() throws IOException, FontFormatException {
        try {

            printOutput("Hello and Good Evening ! ");
            printOutput("Welcome to ChildrenOfTime ! ");
            printOutput("These are your heroes : ");
            players.get(0).showCurrentHeroStats();
            for (Battle battle : battles) {
                while (battle.battleState != BattleState.finished) {
                    switch (battle.battleState) {
                        case story:
                            battle.returnFoesToOtherClassesInYourStaticField();
                            battle.playStory();
                            getUserInput(battle);
                            break;
                        case information:
                            players.get(0).showCurrentHeroStats();
                            battle.showCurrentFoeStats();
                            getUserInput(battle);
                            break;
                        case upgradeSession:
                            battle.startUpgradeSession(firstTime);


                            break;
                        case storeSession:
                            battle.startStoreSession(firstTime);
                            break;
                        case fight:
                            startFight(battle);
                            break;
                    }
                }

                if (players.get(0).isDefeated()) {
                    battle.defeat();
                    break;
                } else {
                    battle.victory();
                    battle.giveReward();
                }
            }

            if (players.get(0).isDefeated()) {
                singlePlayerGameOver();
            } else {
                singlePlayerGameCompleted();
            }

        } catch (GameException gameException) {
            printOutput(gameException.getMessage());
            gameException.printStackTrace();
        }

    }
    private boolean battleIsFinishing(Battle battle) {
        boolean playersAreDefeated = true;
        for (Object p : players) {
            if (!p.isDefeated()) playersAreDefeated = false;
        }
        return battle.checkFoesAreDied() || playersAreDefeated;
    }
    private void startFight(Battle battle) throws IOException, FontFormatException {
        printOutput("Battle #" + battle.id + ":");
        printOutput("Fight : ");
        while (battle.battleState != BattleState.finished) {
            Object currentPlayer = ChildrenOfTime.getInstance().getPlayers().get(0);

            if (!currentPlayer.isDefeated()) {

                printOutput("\n Next Turn: \n");
                battle.initiateNextTurn();
                if (battleIsFinishing(battle)) {
                    battle.battleState = BattleState.finished;

                    if (currentPlayer.isDefeated()) battle.defeat();
                    else printOutput("“Victory! You’ve defeated all of your enemies”");
                }
            }

            ChildrenOfTime.getInstance().firstTime = false;   //TODO Make sure about working correctly ;
        }
    }
    private void singlePlayerGameCompleted() {
        String victoryMessage;
        victoryMessage = "The collector falls down on his knees, he’s strained and desperate but still tries to\n" +
                "drag himself toward Epoch. He knows his era has come to an end. The ancient time machine\n" +
                "calls you to end the disorder and bring unity under its glorious wings, now it’s your\n" +
                "turn to be the MASTERS OF TIME!";
        printOutput("Congratulations! You Won!");
        printOutput(victoryMessage);
    }
    private void singlePlayerGameOver() {
        printOutput("OOPS! You Lose! Try Again!");
    }
    private void getUserInput(Battle battle) {
        String userInput = getInput();

        switch (userInput) {
            case "again":
                againCommand(battle);
                getUserInput(battle);
                break;
            case "help":
                helpCommand(battle);
                getUserInput(battle);
                break;
            case "done":
                doneCommand(battle);
                break;
            default:
                Pattern p = Pattern.compile("\\w+\\?");
                Matcher m = p.matcher(userInput);
                boolean matchFound = m.matches();

                if (matchFound) {
                    String temp = userInput.substring(0, userInput.length() - 1);
                    informationInputIterpreter(temp);
                } else {
                    printOutput("Invalid Command!");
                }
                getUserInput(battle);
                break;
        }

    }
    private void informationInputIterpreter(String userInput) {
        try {
            printOutput(TypesOfFoes.valueOf(userInput).description);

        } catch (Exception e1) {
            try {
                printOutput(TypesOfHero.valueOf(userInput).classDescription);
            } catch (Exception e2) {
                try {
                    printOutput(SupporterHero.valueOf(userInput).heroDescription);

                } catch (Exception e3) {
                    try {
                        printOutput(FighterHero.valueOf(userInput).heroDescription);

                    } catch (Exception e4) {
                        printOutput("Invalid Command!");
                    }
                }
            }
        }
    }
    public void doneCommand(Battle battle) {
        this.firstTime = true;

        switch (battle.battleState) {
            case story:
                battle.battleState = BattleState.information;
                break;
            case information:
                battle.battleState = BattleState.upgradeSession;
                break;
            case upgradeSession:
                battle.battleState = BattleState.storeSession;
                break;
            case storeSession:
                battle.battleState = BattleState.fight;
                break;
            case fight:
                battle.battleState = BattleState.finished;
                break;
        }

    }
    public void helpCommand(Battle battle) {
        switch (battle.battleState) {
            case story:
                battle.storyHelp();
                break;
            case information:
                battle.informationHelp();
                break;
            case upgradeSession:
                battle.upgradeHelp();
                break;
            case storeSession:
                battle.storeHelp();
                break;
            case fight:
                battle.fightHelp();
                break;
        }

    }
    private void againCommand(Battle battle) {

        switch (battle.battleState) {
            case story:
                battle.playStory();
                break;
            case information:
                players.get(0).showCurrentHeroStats();
                battle.showCurrentFoeStats();
                break;
            case upgradeSession:
                battle.startUpgradeSession(true);
                break;
            case storeSession:
                battle.startStoreSession(true);
                break;
            case fight:
                battle.showCurrentFoeStats();
                for (Object player : players) {
                    player.showCurrentHeroStats();
                }
                break;
        }


    }
    public ArrayList<Object> getPlayers() {
        return players;
    }


*/
    }
    public static void showLoadingScreen() {
        frame = new JFrame("Children of Time");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setIconImage(new ImageIcon("src/ui/icon/app_icon.png").getImage());
        frame.setResizable(false);
        LoadingScreenPanel loadingScreenPanel = new LoadingScreenPanel();
        frame.setContentPane(loadingScreenPanel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
//        try {
//            loadingScreenPanel.start();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        GamePanel panel = new GamePanel();
//        JPanel mainPanel = new JPanel(new BorderLayout());
//        JLabel titleLabel = new JLabel("   Amazing " +
//                "  Brick");
//        titleLabel.setFont(new Font("Serif", Font.PLAIN, 45));
//        mainPanel.setPreferredSize(new Dimension(AmazingBrickPanel.WIDTH, AmazingBrickPanel.HEIGHT));
//        titleLabel.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
//        mainPanel.add(titleLabel,BorderLayout.CENTER);
//        JButton playButton = new JButton("Play");
//        playButton.setBorder(BorderFactory.createEmptyBorder(10,20,50,20));
//        mainPanel.add(playButton, BorderLayout.PAGE_END);
//        frame.getContentPane().add(mainPanel);
//        playButton.addActionListener(e -> {
//            frame.getContentPane().removeAll();
//            frame.getContentPane().add(panel);
//            frame.revalidate();
//            panel.requestFocus();
//        });
//        GameEngine engine = new GameEngine(panel);
//        GameController controller = new GameController();
//        panel.init(controller, engine);
//        controller.init(panel, engine);
//        frame.requestFocus();
//        controller.start();
    }
    public static void showMainMenuScreen() {

        MainMenuScreenPanel mainMenuScreenPanel = new MainMenuScreenPanel();
        frame.getContentPane().removeAll();
        frame.setContentPane(mainMenuScreenPanel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        mainMenuScreenPanel.requestFocus();
        mainMenuScreenPanel.repaint();
    }
    public static void changeContentPane(MenuScreenPanel newContentPane) {
        MenuScreenPanel currentContentPane = (MenuScreenPanel)frame.getContentPane();
        currentContentPane.fade();
        frame.getContentPane().removeAll();
        frame.setContentPane(newContentPane);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        newContentPane.requestFocus();
        newContentPane.repaint();
        newContentPane.emerge();

    }
}


