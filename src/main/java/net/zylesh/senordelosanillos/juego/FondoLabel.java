package net.zylesh.senordelosanillos.juego;

import net.zylesh.senordelosanillos.batalla.CampoDeBatalla;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class FondoLabel extends JLabel {

    public FondoLabel(CampoDeBatalla campoDeBatalla) throws IOException {
        setIcon(init(campoDeBatalla));
    }

    public ImageIcon init(CampoDeBatalla campoDeBatalla) throws IOException {
        BufferedImage originalImage = ImageIO.read(campoDeBatalla.getFondo());
        BufferedImage newImg = new BufferedImage(Toolkit.getDefaultToolkit().getScreenSize().width, Toolkit.getDefaultToolkit().getScreenSize().height, originalImage.getType());
        Graphics2D g2d = newImg.createGraphics();
        g2d.drawImage(originalImage, 0, 0, Toolkit.getDefaultToolkit().getScreenSize().width, Toolkit.getDefaultToolkit().getScreenSize().height, null);
        g2d.dispose();
        return new ImageIcon(newImg);
    }
}
