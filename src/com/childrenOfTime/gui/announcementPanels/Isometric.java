package com.childrenOfTime.gui.announcementPanels;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Isometric extends JPanel {
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.getContentPane().add(new Isometric());
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

        List<Color> colorList = new ArrayList<>();
        List<float[]> quads = new ArrayList<>();
        List<Boolean> isTile = new ArrayList<>();

        // create tile grid
        for (int y = 0; y < tileCount; y++) {
            for (int x = 0; x < tileCount; x++) {
                float[] point = new float[3];
                point[0] = x;
                point[1] = 0.0f;
                point[2] = y;

                quads.add(point);
                colorList.add(colors[(int) (Math.random() * colors.length)]);
                isTile.add(Boolean.TRUE);
            }
        }

        // nice arcs (3d)
        for (int k = 0; k <= tileCount; k += 4) {
            for (int i = 0; i <= 10000; i++) {
                float v = k / (float) tileCount;
                float u = i / 10000.0f;

                float[] point = new float[3];
                point[0] = v * (tileCount - 1);
                point[1] = (float) -Math.sqrt(Math.sin(u * Math.PI)) * 5.0f;
                point[2] = u * (tileCount - 1);

                quads.add(point);
                colorList.add(Color.BLACK);
                isTile.add(Boolean.FALSE);
            }
        }

        // render top down
        for (int i = 0; i < quads.size(); i++) {
            float[] point = quads.get(i);

            int x = (int) point[0];
            int y = (int) point[2];

            g.setColor(colorList.get(i));
            g.fillRect(x * tileSize, y * tileSize, tileSize, tileSize);
        }

        // render isometric
        Matrix4 iso = Isometric.createIsometricMatrix(10, this.getWidth() / 2, this.getHeight() / 2);

        final float[] view = new float[3];

        final float[] xRel = new float[]{-0.5f, -0.5f, +0.5f, +0.5f};
        final float[] yRel = new float[]{-0.5f, +0.5f, +0.5f, -0.5f};

        for (int i = 0; i < quads.size(); i++) {
            g.setColor(colorList.get(i));

            if (!isTile.get(i).booleanValue()) {
                float[] model = quads.get(i).clone();

                iso.transform(model, view);

                g.fillOval((int) view[0] - 2, (int) view[1] - 2, 5, 5);
            } else {
                Polygon p = new Polygon();
                for (int k = 0; k < 4; k++) {
                    float[] model = quads.get(i).clone();

                    // translate to tile corners in model space
                    model[0] += xRel[k];
                    model[2] += yRel[k];

                    iso.transform(model, view);

                    p.addPoint((int) view[0], (int) view[1]);
                }

                g.fillPolygon(p);
            }
        }
    }

    private static Matrix4 createIsometricMatrix(int scale, int x, int y) {
        // --> 1.0, 0.0, -1.0, 0.0 // x
        // --> 0.5, 2.0, 0.5, 0.0 // y
        // --> 0.0, -0.05, 0.0, 0.0 // depth
        // --> 0.0, 0.0, 0.0, 1.0 // [nothing]

        Matrix4 iso = new Matrix4();
        iso.m00 = iso.m33 = 1.0f * scale;
        iso.m10 = iso.m12 = 0.5f * scale;
        iso.m11 = 2.0f * scale;
        iso.m02 = -1.0f * scale;
        iso.m21 = -0.05f * scale;
        iso.m03 = x;
        iso.m13 = y;
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