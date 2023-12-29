package net.zylesh.senordelosanillos.juego;

import net.zylesh.senordelosanillos.common.Entidad;
import net.zylesh.senordelosanillos.common.Personaje;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Implementación propia de JLabel para definir un tamaño idéntico entre imágenes independientemente de su tamaño inicial.
 */
public class PersonajeLabel extends JLabel {

    private static final int height = 280;

    private final Entidad entidad;

    public PersonajeLabel(Entidad entidad) throws IOException {
        this.entidad = entidad;
        init(entidad.getPersonaje());
    }

    private void init(Personaje personaje) throws IOException {
        BufferedImage originalImage = ImageIO.read(personaje.getImagen());
        double p = ((height * 100.0) / originalImage.getHeight());
        int width = (int) (originalImage.getWidth() * (p / 100.0));
        BufferedImage newImg = new BufferedImage(width, height, originalImage.getType());
        Graphics2D g2d = newImg.createGraphics();
        g2d.drawImage(originalImage, 0, 0, width, height, null);
        g2d.dispose();
        ImageIcon ic = new ImageIcon(newImg);
        setIcon(ic);
    }

    public Entidad getEntidad() {
        return entidad;
    }

    public static int getAltura() {
        return height;
    }
}
