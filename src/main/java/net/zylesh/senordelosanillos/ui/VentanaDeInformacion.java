package net.zylesh.senordelosanillos.ui;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

/**
 * Implementación propia de diálogo simple.
 */
public class VentanaDeInformacion extends JFrame {

    public static final int INFO = 0;
    public static final int WARN = 1;
    public static final int ERROR = 2;

    public static void mostrarVentana(int tipo, String titulo, String... mensaje) {
        VentanaDeInformacion f = new VentanaDeInformacion(tipo, titulo, mensaje);
        f.setVisible(true);
    }

    private VentanaDeInformacion(int tipo, String titulo, String... mensaje) {
        BufferedImage icon = null;
        try {
            switch (tipo) {
                case WARN: {
                    icon = ImageIO.read(getClass().getClassLoader().getResourceAsStream("assets" + File.separator + "interfaz" + File.separator + "informacion" + File.separator + "warn.png"));
                    break;
                }
                case ERROR: {
                    icon = ImageIO.read(getClass().getClassLoader().getResourceAsStream("assets" + File.separator + "interfaz" + File.separator + "informacion" + File.separator + "error.png"));
                    break;
                }
                case INFO:
                default: {
                    icon = ImageIO.read(getClass().getClassLoader().getResourceAsStream("assets" + File.separator + "interfaz" + File.separator + "informacion" + File.separator + "info.png"));
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (icon != null) setIconImage(icon);
        SpringLayout layout = new SpringLayout();
        setLayout(layout);
        setResizable(false);
        setTitle(titulo);
        setAlwaysOnTop(true);
        Dimension dim2 = new Dimension(510, 100);
        Dimension dim = new Dimension(600, 230);
        int i = 0;
        int baseOffset = 10;
        int textDistanceBetween = 16;
        for (String m : mensaje) {
            JLabel label = new JLabel(m);
            label.setFont(new Font("Comic Sans MS", Font.PLAIN, 13));
            label.setPreferredSize(dim2);
            add(label);
            layout.putConstraint(SpringLayout.WEST, label, 90, SpringLayout.WEST, this);
            layout.putConstraint(SpringLayout.NORTH, label, baseOffset + (i * textDistanceBetween), SpringLayout.NORTH, this);
            i++;
        }
        setSize(dim);
        setPreferredSize(dim);
        JButton okButton = new JButton("Ok");
        Dimension dim1 = new Dimension(140, 35);
        okButton.setSize(dim1);
        okButton.setPreferredSize(dim1);
        okButton.setLocation(60, 70);
        okButton.addActionListener(e -> VentanaDeInformacion.this.setVisible(false));
        double p = ((50 * 100.0) / icon.getWidth());
        int height = (int) (icon.getHeight() * (p / 100.0));
        BufferedImage newImg = new BufferedImage(50, height, icon.getType());
        Graphics2D g2d = newImg.createGraphics();
        g2d.drawImage(icon, 0, 0, 50, height, null);
        g2d.dispose();
        Dimension imgDim = new Dimension(52, 60);
        JLabel image = new JLabel(new ImageIcon(newImg)); image.setSize(imgDim); image.setPreferredSize(imgDim);
        add(image);
        add(okButton);
        layout.putConstraint(SpringLayout.WEST, image, 15, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, image, 25, SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.WEST, okButton, 225, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, okButton, 150, SpringLayout.NORTH, this);
        setLocationRelativeTo(null);
        getRootPane().setDefaultButton(okButton);
    }
}
