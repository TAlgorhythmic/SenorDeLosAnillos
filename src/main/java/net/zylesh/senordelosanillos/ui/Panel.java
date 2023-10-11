package net.zylesh.senordelosanillos.ui;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;

public class Panel extends JPanel {

    private BufferedImage background;
    private final BufferedImage originalImage;
    private final VentanaPrincipal cont;
    private Graphics g;
    private final SpringLayout layout = new SpringLayout();
    private final Map<Component, double[]> components = new HashMap<>();

    Panel(VentanaPrincipal cont) throws IOException {
        super();
        this.cont = cont;
        this.background = ImageIO.read(getClass().getClassLoader().getResourceAsStream("assets/interfaz" + File.separator + "background.png"));
        this.originalImage = background;
        this.setLayout(layout);
    }

    @Override
    protected void paintComponent(Graphics g) {
        this.g = g;
        super.paintComponent(g);
        resizeImage0(cont.getSize(), g);
    }

    public void add(Component comp, double x, double y) {
        add(comp);
        int[] relative = getRelative(comp, x, y);
        layout.putConstraint(SpringLayout.WEST, comp, relative[0], SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, comp, relative[1], SpringLayout.NORTH, this);
        components.put(comp, new double[] {x, y});
    }

    private int[] getRelative(Component comp, double x, double y) {
        int maxX = getSize().width - comp.getPreferredSize().width;
        int maxY = getSize().height - comp.getPreferredSize().height;
        double relativeX = maxX * x;
        double relativeY = maxY * y;
        return new int[] {(int) relativeX, (int) relativeY};
    }

    private void resizeImage0(Dimension dim, Graphics g) {
        double p = ((dim.width * 100.0) / originalImage.getWidth());
        int height = (int) (originalImage.getHeight() * (p / 100.0));
        BufferedImage newImg = new BufferedImage(dim.width, height, background.getType());
        Graphics2D g2d = newImg.createGraphics();
        g2d.drawImage(originalImage, 0, 0, dim.width, height, null);
        g2d.dispose();
        background = newImg;
        g.drawImage(background, 0, 0, this);
    }

    private void resizeImage(Dimension dim) {
        double p = ((dim.width * 100.0) / originalImage.getWidth());
        int height = (int) (originalImage.getHeight() * (p / 100.0));
        BufferedImage newImg = new BufferedImage(dim.width, height, background.getType());
        Graphics2D g2d = newImg.createGraphics();
        g2d.drawImage(originalImage, 0, 0, dim.width, height, null);
        g2d.dispose();
        background = newImg;
        if (g != null) g.drawImage(background, 0, 0, this);
    }

    private static final Timer timer =new Timer();

    @Override
    @SuppressWarnings("deprecation")
    public void resize(Dimension newSize) {
        super.resize(newSize);
        setPreferredSize(newSize);
        if (newSize.width > 0 && newSize.height > 0) {
            resizeImage(newSize);
            for (Component comp : components.keySet()) {
                double[] relative = components.get(comp);
                layout.putConstraint(SpringLayout.WEST, comp, (int) (newSize.width * relative[0]), SpringLayout.WEST, Panel.this);
                layout.putConstraint(SpringLayout.NORTH, comp, (int) (newSize.height * relative[1]), SpringLayout.NORTH, Panel.this);
            }
        }
    }
}
