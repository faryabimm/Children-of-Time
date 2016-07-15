package com.childrenOfTime.gui.announcementPanels;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class IsometricBeta extends JPanel {
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.getContentPane().add(new IsometricBeta());
        frame.setSize(640, 480);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(Color.WHITE);

        final int tileCount = 16;
        final int tileSize = 8;

        Color[] colors = new Color[3];
        colors[0] = Color.RED;
        colors[1] = Color.GREEN;
        colors[2] = Color.BLUE;

        Color[] tileColors = new Color[tileCount * tileCount];
        float[][] tileMidPoints = new float[tileCount * tileCount][3];

        // create random world
        for (int y = 0; y < tileCount; y++) {
            for (int x = 0; x < tileCount; x++) {
                int i = y * tileCount + x;

                tileColors[i] = colors[(int) (Math.random() * colors.length)];

                float[] point = tileMidPoints[i];
                point[0] = x;
                point[1] = 0.0f;
                point[2] = y;
            }
        }

        // render top down
        for (int i = 0; i < tileCount * tileCount; i++) {
            int x = i % tileCount;
            int y = i / tileCount;

            g.setColor(tileColors[i]);
            g.fillRect(x * tileSize, y * tileSize, tileSize, tileSize);
        }

        // render isometric
        Matrix4 iso = IsometricBeta.createIsometricMatrix();

        for (int i = 0; i < tileCount * tileCount; i++) {
            float[] model = tileMidPoints[i];
            float[] view = new float[3];

            iso.transform(model, view);

            float x = view[0];
            float y = view[1];

            // scale
            x *= 10.0f;
            y *= 10.0f;

            // translate
            x += this.getWidth() / 2;
            y += this.getHeight() / 2;

            int xi = (int) x;
            int yi = (int) y;
            int r = 4;

            g.setColor(tileColors[i]);
            g.fillRect(xi - r, yi - r, r * 2 + 1, r * 2 + 1);
        }
    }

    private static Matrix4 createIsometricMatrix() {
        // --> 1.0, 0.0, -1.0, 0.0 // x
        // --> 0.5, 2.0, 0.5, 0.0 // y
        // --> 0.0, -0.05, 0.0, 0.0 // depth
        // --> 0.0, 0.0, 0.0, 1.0 // [nothing]

        Matrix4 iso = new Matrix4();
        iso.m00 = iso.m33 = 1.0f;
        iso.m10 = iso.m12 = 0.5f;
        iso.m11 = 2.0f;
        iso.m02 = -1.0f;
        iso.m21 = -0.05f;
        return iso;
    }

    public static class Matrix4 {
        public float m00, m01, m02, m03;
        public float m10, m11, m12, m13;
        public float m20, m21, m22, m23;
        public float m30, m31, m32, m33;

        public void transform(float[] in, float[] out) {
            float x = in[0];
            float y = in[1];
            float z = in[2];

            out[0] = m00 * x + m01 * y + m02 * z + m03;
            out[1] = m10 * x + m11 * y + m12 * z + m13;
            out[2] = m20 * x + m21 * y + m22 * z + m23;
        }
    }
}