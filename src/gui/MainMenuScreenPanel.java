package gui;

import com.childrenOfTime.model.ChildrenOfTime;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by mohammadmahdi on 7/7/16.
 */
public class MainMenuScreenPanel extends JPanel {

    public static final int ELEMENT_GAP = 20;

    private float pageOpacity = 0f;


    public MainMenuScreenPanel() {
        this.setLayout(null);
        this.setPreferredSize(ChildrenOfTime.PREFERRED_DIMENSION);
        this.initialize();
    }

    private void initialize() {
        JButton singlePlayerButton = new CustomizedJButton("Single Player");
        JButton pvpGameModeButton = new CustomizedJButton("PvP");
        JButton customGameButton = new CustomizedJButton("Game Editor");
        JButton settingsButton = new CustomizedJButton("Options");
        JButton quitButton = new CustomizedJButton("Quit the Game");

        this.add(singlePlayerButton);
        this.add(pvpGameModeButton);
        this.add(customGameButton);
        this.add(settingsButton);
        this.add(quitButton);

        quitButton.setLocation(ELEMENT_GAP,
                ChildrenOfTime.PREFERRED_HEIGHT - CustomizedJButton.MAIN_MENU_BUTTON_HEIGHT - ELEMENT_GAP);
        settingsButton.setLocation(ELEMENT_GAP,
                ChildrenOfTime.PREFERRED_HEIGHT - 2*CustomizedJButton.MAIN_MENU_BUTTON_HEIGHT - 2*ELEMENT_GAP);
        customGameButton.setLocation(ELEMENT_GAP,
                ChildrenOfTime.PREFERRED_HEIGHT - 3*CustomizedJButton.MAIN_MENU_BUTTON_HEIGHT - 3*ELEMENT_GAP);
        pvpGameModeButton.setLocation(ELEMENT_GAP,
                ChildrenOfTime.PREFERRED_HEIGHT - 4*CustomizedJButton.MAIN_MENU_BUTTON_HEIGHT - 4*ELEMENT_GAP);
        singlePlayerButton.setLocation(ELEMENT_GAP,
                ChildrenOfTime.PREFERRED_HEIGHT - 5*CustomizedJButton.MAIN_MENU_BUTTON_HEIGHT - 5*ELEMENT_GAP);

        quitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });


        timer.start();
    }



    private Timer timer = new Timer(LoadingScreenPanel.TIMER_STARTING_DELAY, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            pageOpacity += LoadingScreenPanel.LOGO_OPACITY_INCREMENT;
            if (pageOpacity >= 1) {
                pageOpacity = 1;
                timer.stop();
            }
            repaint();
        }
    });



    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(ChildrenOfTime.GREY);
        g2d.fillRect(0,0,ChildrenOfTime.PREFERRED_WIDTH,ChildrenOfTime.PREFERRED_HEIGHT);
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, pageOpacity));
    }


}
