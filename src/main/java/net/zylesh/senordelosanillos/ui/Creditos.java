package net.zylesh.senordelosanillos.ui;

import javax.swing.*;
import java.awt.*;

public class Creditos extends JFrame {

    private final static Creditos f;
    static {
        f = new Creditos();
    }

    public static void mostrarCreditos() {
        f.setVisible(true);
    }

    private Creditos() {
        Dimension dim = new Dimension(400, 210);
        setSize(dim);
        JPanel background = new JPanel();
        background.setBackground(Color.WHITE);
        setPreferredSize(dim);
        setResizable(false);
        setAlwaysOnTop(true);
        setTitle("Créditos");
        setIconImage(VentanaPrincipal.icon);
        Dimension dim2 = new Dimension(280, 100);
        JLabel label = new JLabel("Ícono/Logo: @WocinGFX");
        Font font = new Font("Arial", Font.BOLD, 16);
        label.setFont(font);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setVerticalAlignment(SwingConstants.CENTER);
        label.setSize(dim2);
        label.setPreferredSize(dim2);
        background.add(label);
        add(background);
        setLocationRelativeTo(null);
    }
}
