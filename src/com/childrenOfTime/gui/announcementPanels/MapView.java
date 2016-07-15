package com.childrenOfTime.gui.announcementPanels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsEnvironment;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.Transparency;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MapView {

    public static void main(String[] args) {
        JFrame test = new JFrame("IsoView");
        test.setSize(800, 600);
        MapViewPane pane = new MapViewPane();
        test.getContentPane().setLayout(new BorderLayout());
        test.getContentPane().add(pane, BorderLayout.CENTER);
        test.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        test.setVisible(true);
    }

    private static class MapViewPane extends JPanel
            implements MouseMotionListener, MouseListener {

        private BufferedImage BackImage;
        BufferedImage GrassTile, SelectedBorder;
        private Point MousePoint, PrevView, ViewLocation, Selected;
        private boolean Dragging;
        private int mapwidth, mapheight, tilecount;

        public MapViewPane() {
            super();
            this.setOpaque(true);
            createAssets();
            tilecount = 30;
            mapwidth = GrassTile.getWidth() * tilecount;
            mapheight = GrassTile.getHeight() * tilecount;
            ViewLocation = new Point(0, mapheight / 2);
            Selected = new Point(-1, -1);
            addMouseListener(this);
            addMouseMotionListener(this);
        }

        private void createAssets() {
            GraphicsConfiguration gc =
                    GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration();
            GrassTile = gc.createCompatibleImage(128,
                    64, Transparency.TRANSLUCENT);
            Graphics g = GrassTile.getGraphics();
            Polygon poly = new Polygon();
            poly.addPoint(0, 32);
            poly.addPoint(64, 0);
            poly.addPoint(128, 32);
            poly.addPoint(64, 64);
            g.setColor(Color.GREEN);
            g.fillPolygon(poly);
            g.setColor(Color.BLUE);
            g.drawPolygon(poly);
            g.dispose();
            SelectedBorder = gc.createCompatibleImage(128,
                    64, Transparency.TRANSLUCENT);
            g = SelectedBorder.getGraphics();
            g.setColor(Color.red);
            g.drawPolygon(poly);
            g.dispose();
        }

        @Override
        public void paint(Graphics g) {
            //super.paint(g);
            Rectangle visiblerec = this.getVisibleRect();
            g.setColor(Color.BLACK);
            g.fillRect(visiblerec.x, visiblerec.y,
                    visiblerec.width, visiblerec.height);
            checkBackImage();
            Graphics bg = BackImage.getGraphics();
            drawGrassGrid(bg);
            bg.dispose();
            g.drawImage(BackImage, 0, 0, this);
        }

        private void drawGrassGrid(Graphics g) {
            int dx = 0;
            int dy = 0;
            g.setColor(Color.BLACK);
            g.fillRect(0, 0, BackImage.getWidth(), BackImage.getHeight());
            for (int x = 0; x < tilecount; x++) {
                for (int y = 0; y < tilecount; y++) {
                    dx = x * GrassTile.getWidth() / 2
                            - y * GrassTile.getWidth() / 2;
                    dy = x * GrassTile.getHeight() / 2
                            + y * GrassTile.getHeight() / 2;
                    dx -= ViewLocation.x;
                    dy -= ViewLocation.y;
                    g.drawImage(GrassTile, dx, dy, this);
                    if ((x == Selected.x) && (y == Selected.y)) {
                        g.drawImage(SelectedBorder, dx, dy, this);
                    }
                    g.drawString("(" + x + "," + y + ")", dx, dy
                            + GrassTile.getHeight() / 2);
                }
            }
        }

        private void checkBackImage() {
            if ((BackImage == null) || (BackImage.getWidth() != this.getWidth())
                    || (BackImage.getHeight() != this.getHeight())) {
                GraphicsConfiguration gc =
                        GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration();
                BackImage = gc.createCompatibleImage(this.getWidth(),
                        this.getHeight(), Transparency.BITMASK);
            }
        }

        @Override
        public void mouseDragged(MouseEvent e) {
            if (Dragging) {
                ViewLocation.x = PrevView.x + MousePoint.x - e.getX();
                ViewLocation.y = PrevView.y + MousePoint.y - e.getY();
                if (ViewLocation.x < -mapwidth / 2) {
                    ViewLocation.x = -mapwidth / 2;
                }
                if (ViewLocation.y < -mapheight / 2 + this.getHeight()) {
                    ViewLocation.y = -mapheight / 2 + this.getHeight();
                }
                if (ViewLocation.x > mapwidth / 2 - this.getWidth()
                        + GrassTile.getWidth()) {
                    ViewLocation.x = mapwidth / 2 - this.getWidth()
                            + GrassTile.getWidth();
                }
                if (ViewLocation.y > mapheight / 2 + this.getHeight()) {
                    ViewLocation.y = mapheight / 2 + this.getHeight();
                }
                repaint();
            }
        }

        @Override
        public void mouseMoved(MouseEvent e) {
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            if (!Dragging) {
            /*
            // copy of the tile location assignment code as a reminder
            dx = x * GrassTile.getWidth() / 2
                    - y * GrassTile.getWidth() / 2;
            dy = x * GrassTile.getHeight() / 2
                    + y * GrassTile.getHeight() / 2;
            dx -= ViewLocation.x;
            dy -= ViewLocation.y;
            */
                int pickX = e.getX() + ViewLocation.x;
                int pickY = e.getY() + ViewLocation.y;
                int tileW = GrassTile.getWidth();
                int tileH = GrassTile.getHeight();
            /*
            // assignment code refactored
            x - y = 2 * pickX / tileW;
            x + y = 2 * pickY / tileH;

            // x+y= refactored to y=
            y = (2*pickY / tileH) - x;
            // substitute into x-y + refactor
            2x = (2 * pickX / tileW) + (2 * pickY / tileH);

            // x+y= refactored to x=
            x = (2*pickY / tileH) - y;
            // substitute x-y + refactor
            -2y = (2 * pickX / tileW) - (2 * pickY / tileH);
            2y = (2 * pickY / tileH) - (2 * pickX / tileW);
            */
                int hitx = (int) (((double) pickX / (double) tileW) + ((double) pickY / (double) tileH) - 0.5);
                int hity = (int) (((double) pickY / (double) tileH) - ((double) pickX / (double) tileW) + 0.5);
                Selected.setLocation(hitx, hity);
                repaint();
                //System.out.println("(" + x + "," + y + ")");
            }
        }

        @Override
        public void mousePressed(MouseEvent e) {
            if ((e.getButton() == MouseEvent.BUTTON1) && !Dragging) {
                MousePoint = e.getPoint();
                PrevView = new Point(ViewLocation);
                Dragging = true;
            }
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            Dragging = false;
            MousePoint = null;
        }

        @Override
        public void mouseEntered(MouseEvent e) {
        }

        @Override
        public void mouseExited(MouseEvent e) {
        }
    }
}