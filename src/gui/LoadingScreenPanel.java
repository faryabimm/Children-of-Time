package gui;

import com.childrenOfTime.model.ChildrenOfTime;
import javafx.scene.image.*;

import javax.swing.*;
import java.awt.*;
import java.awt.Image;

/**
 * Created by mohammadmahdi on 7/7/16.
 */
public class LoadingScreenPanel extends JPanel {

    private int logoOpacity = 0;
    public static final int LOGO_OPACITY_INCREAMENT = 5;
    private boolean opacityRising = true;
    Image logo = null;



    public LoadingScreenPanel () {
        this.setLayout(null);
        this.setPreferredSize(ChildrenOfTime.PREFERRED_DIMENSION);
        this.initialize();
    }

    private void initialize() {


        Toolkit kit = Toolkit.getDefaultToolkit();
        logo = kit.getImage("src/ui/icon/app_icon.png");
        logo = logo.getScaledInstance(ChildrenOfTime.PREFERRED_WIDTH/3, ChildrenOfTime.PREFERRED_WIDTH/3, 0);

    }

    public void start() throws InterruptedException {
        while (logoOpacity < 100 - LOGO_OPACITY_INCREAMENT) {
            this.repaint();
            logoOpacity += LOGO_OPACITY_INCREAMENT;
            Thread.sleep(1000/ChildrenOfTime.FPS);
        }

        while (logoOpacity <= 100 ) {
            this.repaint();
            logoOpacity -= LOGO_OPACITY_INCREAMENT;
            Thread.sleep(1000/ChildrenOfTime.FPS);
        }
    }

    @Override
    protected void paintComponent(Graphics g) {

        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2d.setColor(ChildrenOfTime.GREY);
        g2d.fillRect(0,0,ChildrenOfTime.PREFERRED_WIDTH,ChildrenOfTime.PREFERRED_HEIGHT);

        paintLogo(g2d);
    }

    private void paintLogo(Graphics2D g2d) {
        g2d.setComposite(AlphaComposite.getInstance(
                AlphaComposite.SRC_IN, alpha));
        g2d.drawImage(logo,(ChildrenOfTime.PREFERRED_WIDTH - logo.getWidth(this))/2 ,
                (ChildrenOfTime.PREFERRED_HEIGHT - logo.getHeight(this))/2  , this);

    }

}
